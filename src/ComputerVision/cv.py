import cv2
from collections import deque
import numpy as np
import sys, argparse, imutils
from datetime import datetime
import socket, time;
from enum import Enum;

ap = argparse.ArgumentParser();
ap.add_argument(
    "-b",
    "--buffer",
    type = int,
    default = 32,
	help = "max buffer size")
args = vars(ap.parse_args())

# define the lower and upper boundaries of the "green"
# ball in the HSV color space, then initialize the
# list of tracked points
Orange1Upper = (28,216,294)
Orange1Lower = (6,142,161)
# OrangeLower = (0,145,200)
# OrangeUpper = (30,200,300)

# global variables
BLUE = [255,0,0]
GREEN = [0,255,0]
RED = [0,0,255]
BORDERWIDTH = 5

# up/down travel variables
minMax = -1
rising = False
init = False

# ball state FSM
fsm = 0 # 0 - waiting request, 1 - detecting, 2 - active ball, 3 - descent

# global variables
HOST = "localhost";
PORT = 9003;

socketIn = socket.socket();             # handles incoming requests
socketIn.bind((HOST, PORT));            # binds to a host and port
socketIn.listen(5);                     # enables server to accept incoming

pts = deque(maxlen = args["buffer"])
counter = 0
(dX, dY) = (0,0)
cap = cv2.VideoCapture(1)
print(cap.get(3))
print(cap.get(4))

ok,frame = cap.read()
while(True):
    
    if(fsm == 0):                           # waiting on connection
        
        conn, addr = socketIn.accept();     # loop will freeze on this command, runs when SmartServe
        print("Got connection from", addr); # sends a request
        msg = conn.recv(1024);              # parses msg... could be "TEST", "DETECT"
    
        # DEBUGGING
        # msg = b'DETECT'
        # print(msg);
    
        if("TEST" in msg.decode("UTF8")):
            # DEBUGGING
            time.sleep(1);
            
            # socket for outgoing messages
            socketOut = socket.socket(socket.AF_INET, socket.SOCK_STREAM);
            socketOut.connect((HOST, PORT + 1));
            
            socketOut.send(b'ALLGOOD\n');
        else:
            start = datetime.now();
            fsm = 1;
    
    if(fsm == 1 or fsm == 2 or fsm == 3):
        
        if((datetime.now() - start).seconds > 10):
            # socket for outgoing messages
            socketOut = socket.socket(socket.AF_INET, socket.SOCK_STREAM);
            socketOut.connect((HOST, PORT + 1));
            
            socketOut.send(b'BAD\n');
            fsm = 0;
            pts = deque(maxlen=args["buffer"])

        else:
            # grab the current frame
            (grabbed, frame) = cap.read()
            img = cap.read()
            
            # resize the frame, blur it, and convert it to the HSV
            # color space
            frame = imutils.resize(frame, width=600)
            hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

            # construct a mask for the color "green", then perform
            # a series of dilations and erosions to remove any small
            # blobs left in the mask
            mask = cv2.inRange(hsv, Orange1Lower, Orange1Upper)
            thresh = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
            blurred = cv2.GaussianBlur(thresh, (9, 9), 0)

            mask = cv2.erode(mask, None, iterations=2)
            mask = cv2.dilate(mask, None, iterations=2)

            circles = cv2.HoughCircles(blurred, cv2.HOUGH_GRADIENT, 1, 2000, 25, 250, 10, 10)  # ret=[[Xpos,Ypos,Radius],...]

            if(circles is not None):
                ##mask = cv2.inRange(hsv, OrangeLower, OrangeUpper)
                ##mask = cv2.erode(mask, None, iterations=2)
                ##mask = cv2.dilate(mask, None, iterations=2)
                circles = np.uint16(np.around(circles))

                for i in circles[0, :]:
                    # draw the outer circle
                    cv2.circle(thresh, (i[0], i[1]), i[2], (0, 255, 0), 2)
                    # draw the center of the circle
                    cv2.circle(thresh, (i[0], i[1]), 2, (0, 0, 255), 3)


            # find contours in the mask and initialize the current
            # (x, y) center of the ball
            cnts = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL,
                                    cv2.CHAIN_APPROX_SIMPLE)[-2]
            center = None

            # only proceed if at least one contour was found
            if len(cnts) > 0:
                # find the largest contour in the mask, then use
                # it to compute the minimum enclosing circle and
                # centroid
                c = max(cnts, key=cv2.contourArea)
                ((x, y), radius) = cv2.minEnclosingCircle(c)
                M = cv2.moments(c)
                center = (int(M["m10"] / M["m00"]), int(M["m01"] / M["m00"]))

                # only proceed if the radius meets a minimum size
                if radius > 2:
                    # draw the circle and centroid on the frame,
                    # then update the list of tracked points
                    cv2.circle(frame, (int(x), int(y)), int(radius),
                               (0, 255, 255), 2)
                    cv2.circle(frame, center, 5, (0, 0, 255), -1)
                    pts.appendleft(center)

            # loop over the set of tracked points
            for i in np.arange(1, len(pts)):
                # if either of the tracked points are None, ignore
                # them
                if pts[i - 1] is None or pts[i] is None:
                    continue

                # otherwise, compute the thickness of the line and
                # draw the connecting lines
                thickness = int(np.sqrt(args["buffer"] / float(i + 1)) * 2.5)
                cv2.line(frame, pts[i - 1], pts[i], (0, 0, 255), thickness)
                # check to see if enough points have been accumulated in
                # the buffer
                if counter >= 10 and i == 10 and pts[-10] is not None:
                    # compute the difference between the x and y
                    # coordinates and re-initialize the direction
                    # text variables
                    dX = pts[-10][0] - pts[i][0]
                    dY = pts[-10][1] - pts[i][1]
                    (dirX, dirY) = ("", "")
                    
                    if(fsm == 1):       # check if active
                        print("in state 1")
                        if(dX > 10):
                            fsm = 2;
                    elif(fsm == 2):     # check if descending
                        print("in state 2")
                        if(dY > 10):
                            fsm = 3;
                    elif(fsm == 3):     # if ascending, return GOOD
                        print("in state 3")
                        if(dY < -10):
                            # socket for outgoing messages
                            socketOut = socket.socket(socket.AF_INET, socket.SOCK_STREAM);
                            socketOut.connect((HOST, PORT + 1));
                            print("hit")
                            socketOut.send(b'GOOD\n');
                            fsm = 0;    # HIT!
                            pts = deque(maxlen = args["buffer"])
                    

                thickness = int(np.sqrt(args["buffer"] / float(i + 1)) * 2.5)
                cv2.line(frame, pts[i - 1], pts[i], (0, 0, 255), thickness)
                cv2.putText(frame, "dx: {}, dy: {}".format(dX, dY),
                        (10, frame.shape[0] - 10), cv2.FONT_HERSHEY_SIMPLEX,
                        0.35, (0, 0, 255), 1)

            # show the frame to our screen
            cv2.imshow("Frame", mask)
            border = cv2.copyMakeBorder(
                frame,
                top = BORDERWIDTH,
                bottom = BORDERWIDTH,
                left = BORDERWIDTH,
                right = BORDERWIDTH,
                borderType = cv2.BORDER_CONSTANT,
                value = RED)
            cv2.imshow('border', border)
           ## cv2.imshow("shape",thresh)
            key = cv2.waitKey(1) & 0xFF
            counter += 1

            # if the 'q' key is pressed, stop the l3oop
            if key == ord("q"):
                break