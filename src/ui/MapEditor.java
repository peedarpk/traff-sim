package ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import obj.Edge;
import obj.Node;

public class MapEditor extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 7218471721638221815L;
	public int panelWidth = 120;
	public int panelHeight = 60;
	int nodeCount = 0;
	int edgeCount = 0;
	ArrayList<Node> nodesList = new ArrayList<Node>();
	ArrayList<Edge> edgesList = new ArrayList<Edge>();
	Node[][] nodesMatrix = new Node[120][60];
	boolean rightClick = false;
	Point startDrag, endDrag;

	PopUpMenu popupMenu = new PopUpMenu();
	/** The rendered size of the tile (in pixels) */
	public static final int TILE_SIZE = 10;

	public MapEditor() {
		setBackground(Color.WHITE);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(popupMenu);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void setData(ArrayList<Edge> elist, ArrayList<Node> nList) {
		this.edgesList = elist;
		this.nodesList = nList;
	}

	private Edge makeEdge(Point start, Point end) {

		Node startNode = nodesMatrix[start.x / TILE_SIZE][start.y / TILE_SIZE];

		Node endNode = nodesMatrix[end.x / TILE_SIZE][end.y / TILE_SIZE];

		if(!startNode.equals(endNode)){
			Edge e = new Edge("e" + edgeCount, startNode, endNode);
			return e;
		}
		

		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (rightClick == false) {

			Node n = new Node(e.getX(), e.getY(), "Node" + nodeCount);
			if(nodesMatrix[n.getxBox()][n.getyBox()] == null){
				nodeCount++;
				nodesList.add(n);
				nodesMatrix[n.getxBox()][n.getyBox()] = n;
				this.repaint();
			}
		}
		rightClick = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		endDrag = new Point(e.getX(), e.getY());
		this.repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int xBoxNo = e.getX() / TILE_SIZE;
		int yBoxNo = e.getY() / TILE_SIZE;
		if (e.isPopupTrigger()) {
			rightClick = true;
			if (nodesMatrix[xBoxNo][yBoxNo] != null) {
				popupMenu.setNodeAndPanel(this, nodesList, nodesMatrix[xBoxNo][yBoxNo], nodesMatrix, xBoxNo, yBoxNo, edgesList);
				popupMenu.show(this, e.getX(), e.getY());
			}

		}
		if (rightClick == false) {

			if (nodesMatrix[xBoxNo][yBoxNo] != null) {
				startDrag = new Point(e.getX(), e.getY());
				endDrag = startDrag;
				this.repaint();
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int xBoxNo = e.getX() / TILE_SIZE;
		int yBoxNo = e.getY() / TILE_SIZE;
		if (e.isPopupTrigger()) {
			rightClick = true;
			if (nodesMatrix[xBoxNo][yBoxNo] != null) {
				popupMenu.setNodeAndPanel(this, nodesList, nodesMatrix[xBoxNo][yBoxNo], nodesMatrix, xBoxNo, yBoxNo, edgesList);
				popupMenu.show(this, e.getX(), e.getY());
			}
		}
		if (rightClick == false) {

			if (nodesMatrix[xBoxNo][yBoxNo] != null) {
				Edge edge = makeEdge(startDrag, endDrag);
				
				if(edge != null){
					edgesList.add(edge);
					edgeCount++;
					startDrag = null;
					endDrag = null;
					this.repaint();
				}

			}
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int x = 0; x < panelWidth; x++) {
			for (int y = 0; y < panelHeight; y++) {

				g.setColor(Color.GRAY.brighter());
				g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
		
		// Draw line on mouse drag
		if (startDrag != null && endDrag != null) {
			g2d.setPaint(Color.lightGray);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setStroke(new BasicStroke(20));
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.80f));
			g2d.drawLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
		}
		// Draw edges
		for (Edge e : this.edgesList) {
			g2d.setPaint(Color.LIGHT_GRAY);
			g2d.setStroke(new BasicStroke(20));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.drawLine(e.getStartNode().getxBox() * TILE_SIZE, e.getStartNode().getyBox() * TILE_SIZE, e.getEndNode()
					.getxBox() * TILE_SIZE, e.getEndNode().getyBox() * TILE_SIZE);
			
//			g2d.setPaint(Color.LIGHT_GRAY);
//			g2d.setStroke(new BasicStroke(16));
//			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			g2d.drawLine(e.getStartNode().getxBox() * TILE_SIZE, e.getStartNode().getyBox() * TILE_SIZE, e.getEndNode()
//					.getxBox() * TILE_SIZE, e.getEndNode().getyBox() * TILE_SIZE);

			Point tip = new Point(e.getStartNode().getxBox() * TILE_SIZE, e.getStartNode().getyBox() * TILE_SIZE);
			Point tail = new Point(e.getEndNode().getxBox() * TILE_SIZE, e.getEndNode().getyBox() * TILE_SIZE);
			Point middle = getCenterPoint(tip, tail);
			g2d.setStroke(new BasicStroke(2));
			drawArrowHead(g2d, middle, tip, Color.DARK_GRAY);
			g2d.setPaint(Color.BLUE);
			g2d.drawString(e.getName(), middle.x + 5, middle.y + 5);
		}
		
		
		// draw Nodes
		for (Node n : this.nodesList) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawOval(n.getxBox() * TILE_SIZE, n.getyBox() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			g2d.fillOval(n.getxBox() * TILE_SIZE, n.getyBox() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			g2d.setPaint(Color.BLUE);
			g2d.drawString(n.getName(), n.getxBox() * TILE_SIZE, n.getyBox() * TILE_SIZE);
		}
	}

	double phi = Math.toRadians(40);
	int barb = 8;

	private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color) {
		g2.setPaint(color);
		double dy = tip.y - tail.y;
		double dx = tip.x - tail.x;
		double theta = Math.atan2(dy, dx);
		double x, y, rho = theta + phi;
		for (int j = 0; j < 2; j++) {
			x = tip.x - barb * Math.cos(rho);
			y = tip.y - barb * Math.sin(rho);
			g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
			rho = theta - phi;
		}
	}

	private Point getCenterPoint(Point start, Point end) {
		int x = (end.x + start.x) / 2;
		int y = (end.y + start.y) / 2;
		Point middle = new Point(x, y);

		return middle;
	}

	public int getPanelHeight() {
		return panelHeight;
	}

	public int getPanelWidth() {
		return panelWidth;
	}

	public void setPanelHeight(int panelHeight) {
		this.panelHeight = panelHeight;
	}

	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}

}
