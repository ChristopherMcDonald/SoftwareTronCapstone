import cv2
from collections import deque
import numpy as np
import sys
import argparse
import imutils
print(cv2.__version__)




ap = argparse.ArgumentParser()
ap.add_argument("-b", "--buffer", type=int, default=64,
	help="max buffer size")
args = vars(ap.parse_args())
# define the lower and upper boundaries of the "green"
# ball in the HSV color space, then initialize the
# list of tracked points
Orange1Upper = (50,300,300)
Orange1Lower = (0,160,210)
OrangeLower = (0,145,200)
OrangeUpper = (30,200,300)




pts = deque(maxlen=args["buffer"])
cap = cv2.VideoCapture(0)
print(cap.get(3))
print(cap.get(4))

ok,frame = cap.read()


while(True):
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
        print(circles)
        for i in circles[0, :]:
            # draw the outer circle
            cv2.circle(thresh, (i[0], i[1]), i[2], (0, 255, 0), 2)
            # draw the center of the circle
            cv2.circle(thresh, (i[0], i[1]), 2, (0, 0, 255), 3)
        print("\n")

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

    # update the points queue
    pts.appendleft(center)
    # loop over the set of tracked points
    for i in range(1, len(pts)):
        # if either of the tracked points are None, ignore
        # them
        if pts[i - 1] is None or pts[i] is None:
            continue

        # otherwise, compute the thickness of the line and
        # draw the connecting lines
        thickness = int(np.sqrt(args["buffer"] / float(i + 1)) * 2.5)
        cv2.line(frame, pts[i - 1], pts[i], (0, 0, 255), thickness)

    # show the frame to our screen
    cv2.imshow("Frame", frame)
   ## cv2.imshow("shape",thresh)
    key = cv2.waitKey(1) & 0xFF

    # if the 'q' key is pressed, stop the loop
    if key == ord("q"):
        break

cap.release()
cv2.destroyAllWindows()