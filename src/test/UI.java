package test;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Dimension;

public class UI extends JFrame{
	public UI() {
		setSize(new Dimension(700, 500));
		setVisible(true);
		
		JPanel panel = new BGPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public static void main(String[] args) {
		new UI();
	}
}
