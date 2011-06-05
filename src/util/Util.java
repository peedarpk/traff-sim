package util;

import java.awt.Point;

public class Util {

	public static Point getNextPoint(Point startPoint, Point endPoint, int distance, Double slope) {
		Point nextPoint = null;

		//Double slope = ((endPoint.getX() - startPoint.getX()) / (endPoint.getY() - startPoint.getY()));

		System.out.println((endPoint.getX() - startPoint.getX()) + " / " + (endPoint.getY() - startPoint.getY()));


		//double theta = Math.atan(slope);
		
		double theta = Math.atan2(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
		
		System.out.println("theta: " + theta);

		Double nextX = startPoint.getX() + (distance * Math.cos(theta));

		Double nextY = startPoint.getY() + (distance * Math.sin(theta));

		nextPoint = new Point(nextX.intValue(), nextY.intValue());

		System.out.println(nextPoint);

		return nextPoint;
	}

	public static Point getNextPoint2(Point startPoint, Point endPoint, int distance) {

		Point nextPoint = null;

		Double slope = ((endPoint.getX() - startPoint.getX()) / (endPoint.getY() - startPoint.getY()));

		return nextPoint;
	}

}
