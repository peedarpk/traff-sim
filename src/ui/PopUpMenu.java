package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import obj.Edge;
import obj.Node;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MenuDragMouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopUpMenu extends JPopupMenu{
	
	MapEditor panel;
	ArrayList<Node> list;
	ArrayList<Edge> edgesList = new ArrayList<Edge>();
	Node[][] nodesMatrix = new Node[120][60];
	Node n;
	int xBox;
	int yBox;
	
	public PopUpMenu() {
		
		JMenu mnNewMenu = new JMenu("Remove Edge");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("PopUpMenu.PopUpMenu().new MouseAdapter() {...}.mouseEntered()");
			}
		});
		add(mnNewMenu);
		
		JSeparator separator = new JSeparator();
		add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Remove Node");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Edge> elist = new ArrayList<Edge>();
				elist.addAll(edgesList);
				for(Edge e : elist){
					if(e.getStartNode().equals(n) || e.getEndNode().equals(n)){
						edgesList.remove(e);
					}
				}
				list.remove(n);
				nodesMatrix[xBox][yBox] = null;
				panel.repaint();
			}
		});
		add(mntmNewMenuItem);
	}

	public void setNodeAndPanel(MapEditor panel, ArrayList<Node> list, Node n, Node[][] nodesMatrix, int x, int y, ArrayList<Edge> elist) {
		this.panel = panel;
		this.list = list;
		this.n = n;
		this.nodesMatrix = nodesMatrix;
		this.xBox = x;
		this.yBox = y;
		this.edgesList = elist;
	}
}
