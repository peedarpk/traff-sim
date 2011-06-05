package util;

import java.awt.Point;

public class Util {

	public static Point getNextPoint(Point startPoint, Point endPoint, int distance, Double slope) {
		Point nextPoint = null;

		double theta = getAngle(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());

		Double nextX = startPoint.getX() + (distance * Math.cos(theta));

		Double nextY = startPoint.getY() - (distance * Math.sin(theta));

		nextPoint = new Point(nextX.intValue(), nextY.intValue());

		return nextPoint;
	}

	public static Point getNextPoint2(Point startPoint, Point endPoint, int distance) {

		Point nextPoint = null;

		Double slope = ((endPoint.getX() - startPoint.getX()) / (endPoint.getY() - startPoint.getY()));

		return nextPoint;
	}

	private static double getAngle(double x1, double y1, double x2, double y2) {

		// slope of the line
		double slope = (y2 - y1) / (x2 - x1); // Get gradient of line.

		if (x2 == x1) {// Protect against divide by 0
			if (y2 == y1)
				return -1;
			else if (y2 < y1)
				return Math.PI / 2;
			else
				return 3 * (Math.PI / 2);
		}

		/**
		 * Slope is the opposite sign to what you would expect due
		 * to computer screen geometry differing from math X-Y geometry. Value
		 * to return depends on what quadrant the line is in.
		 */

		if (x2 >= x1 && y2 <= y1) {
			return (-Math.atan(slope)); // 1st quadrant.
		} else if (x2 < x1 && y2 <= y1) {
			return (Math.PI - Math.atan(slope)); // 2nd quadrant.
		} else if (x2 < x1 && y2 > y1) {
			return (Math.PI - Math.atan(slope)); // 3rd quadrant.
		} else {
			return (2 * Math.PI - Math.atan(slope)); // 4th quadrant.
		}
	}

}
