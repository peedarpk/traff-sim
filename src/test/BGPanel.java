package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import util.Util;

public class BGPanel extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(1));
		g2d.drawOval(100, 100, 10, 10);
		g2d.drawOval(500, 200, 10, 10);
		
		Point startPoint = new Point(100, 100);
		Point endPoint = new Point(500, 200);
		Double slope = ((endPoint.getX() - startPoint.getX()) / (endPoint.getY() - startPoint.getY()));
		int count = 0;
		while(count < 20){
			Point next = Util.getNextPoint(startPoint, endPoint, 20, slope);
			
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawOval((int)next.getX(), (int)next.getY(), 10, 10);
			
			startPoint = next;
			count++;
		}

	}

}
