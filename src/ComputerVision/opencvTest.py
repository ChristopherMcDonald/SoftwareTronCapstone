import cv2
from collections import deque
import numpy as np
import argparse
import sys, argparse, imutils
from imutils.video import WebcamVideoStream, FPS
import time;

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
Orange1Upper = (30,256,300)
Orange1Lower = (5,169,150)
OrangeLower = (0,145,200)
OrangeUpper = (30,200,300)

# global variables
BLUE = [255,0,0]
GREEN = [0,255,0]
RED = [0,0,255]
BORDERWIDTH = 5
roiPts = []
inputeMode = True

# up/down travel variables
minMax = -1
rising = False
init = False

# ball state FSM
fsm = 0 # 0 - initial fall, 1 - rise, 2 - out of frame

# detect mode, will exit on return (successful or not)
detect = False

pts = deque(maxlen = args["buffer"])
counter = 0
(dX, dY) = (0,0)
cap = cv2.VideoCapture(1)
vs= WebcamVideoStream(src=1).start()
roiBox = None
roiHist = None
frame = vs.read()
fr_count=0;
start =0;
frames = FPS().start()


while(True and not (fsm != 0 and detect)):
    if(fr_count==0):
        start=time.time()
    # grab the current frame
    frame = vs.read()
    img = vs.read()
    # resize the frame, blur it, and convert it to the HSV
    # color space
    frame = imutils.resize(frame, width=600)
    orig = frame.copy()
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    # construct a mask for the color "green", then perform
    # a series of dilations and erosions to remove any small
    # blobs left in the mask
    termination = (cv2.TERM_CRITERIA_EPS | cv2.TERM_CRITERIA_COUNT, 10, 1)
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
    if(rising):
        borderColour = GREEN;
    else:
        borderColour = RED;

    # only proceed if at least one contour was found
    if len(cnts) > 0:
        # find the largest contour in the mask, then use
        # it to compute the minimum enclosing circle and
        # centroid
        c = max(cnts, key=cv2.contourArea)

        ((x, y), radius) = cv2.minEnclosingCircle(c)

        if(not init):
            minMax = y
            init = True
        else:
            if(rising):
                if(y < minMax):
                    minMax = y
                elif(y > minMax + 10):
                    minMax = y
                    rising = False
                    borderColour = RED
            else:
                if(y < minMax - 10):
                    minMax = y
                    rising = True
                    borderColour = GREEN
                    fsm = 1
                elif(y > minMax):
                    minMax = y
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



    elif(init):
        fsm = 2

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
        value = borderColour)
    cv2.imshow('border', border)
   ## cv2.imshow("shape",thresh)
    key = cv2.waitKey(1) & 0xFF
    frames.update()



    # if the 'q' key is pressed, stop the loop
    if key == ord("q"):
        break

if(fsm == 1):
    txt = "Success!"
else:
    txt = "Fail!"

cv2.putText(frame, txt, (150,160), cv2.FONT_HERSHEY_SIMPLEX, 2, borderColour, 2)
frames.stop()
print("[INFO] elasped time: {:.2f}".format(frames.elapsed()))
print("[INFO] approx. FPS: {:.2f}".format(frames.fps()))
# cap.release()
# cv2.destroyAllWindows()
