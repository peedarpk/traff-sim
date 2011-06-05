package ui;

/**
 * @author peedarpk
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import obj.Edge;
import obj.Node;
import obj.Path;

public class Simulator extends JPanel {

	private static final long serialVersionUID = 3271124102273173061L;
	ArrayList<Path> pathsList = new ArrayList<Path>();
	ArrayList<Edge> edgesList = new ArrayList<Edge>();
	ArrayList<Node> nodesList = new ArrayList<Node>();
	/** The rendered size of the tile (in pixels) */
	public static final int TILE_SIZE = 10;

	public Simulator() {
		setBackground(new Color(34, 139, 34));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}

	public void setData(ArrayList<Edge> elist, ArrayList<Node> nList, ArrayList<Path> pList) {
		this.edgesList = elist;
		this.nodesList = nList;
		this.pathsList = pList;
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ArrayList<GeneralPath> shapesList = getShapesList(pathsList);

		for (GeneralPath shape : shapesList) {
			g2d.setColor(Color.DARK_GRAY);
			g2d.setStroke(new BasicStroke(20.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.draw(shape);

//			g2d.setColor(Color.GRAY);
//			g2d.setStroke(new BasicStroke(16.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
//			g2d.draw(shape);
		}

	}

	private ArrayList<GeneralPath> getShapesList(ArrayList<Path> pathsList) {
		GeneralPath pathShape;
		ArrayList<GeneralPath> shapesList = new ArrayList<GeneralPath>();
		for (Path p : pathsList) {
			pathShape = new GeneralPath();
			ArrayList<Edge> edges = p.getEdgesList();
			int count = 0;
			int[] xpoints = new int[edges.size() * 2];
			int[] ypoints = new int[edges.size() * 2];
			int xValue;
			int yValue;

			for (Edge e : edges) {
				Node sNode = e.getStartNode();
				xValue = sNode.getxBox() * TILE_SIZE;
				yValue = sNode.getyBox() * TILE_SIZE;
				xpoints[count] = xValue;
				ypoints[count] = yValue;
				count++;
				Node eNode = e.getEndNode();
				xValue = eNode.getxBox() * TILE_SIZE;
				yValue = eNode.getyBox() * TILE_SIZE;
				xpoints[count] = xValue;
				ypoints[count] = yValue;
				count++;
			}

			for (int i = 0; i < xpoints.length; i++) {
				if (i == 0) {
					pathShape.moveTo(xpoints[i], ypoints[i]);
				} else {
					pathShape.lineTo(xpoints[i], ypoints[i]);
				}
			}

			shapesList.add(pathShape);

		}

		return shapesList;
	}
}
