package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class NewPanelDialog extends JDialog implements PropertyChangeListener {

	private static final long serialVersionUID = -3778572862783552542L;
	private NumberFormat paymentFormat = NumberFormat.getIntegerInstance();
	
	private int newPanelWidth = 0;
	private int newPanelHeight = 0;
	JFormattedTextField txtHeight;
	JFormattedTextField txtWidth;

	public NewPanelDialog(JFrame frame) {
		super(frame, true);
		setLocation(new Point(300, 300));
		setSize(new Dimension(400, 300));
		setTitle("Map Editor Properties");

		JLabel lblHieght = new JLabel("Height :");
		lblHieght.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblWidth = new JLabel("Width :");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				setVisible(false);
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		JLabel lblpixels = new JLabel("( in pixels )");
		lblpixels.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblInPixels = new JLabel("( in pixels )");
		lblInPixels.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtHeight = new JFormattedTextField(paymentFormat);
		txtHeight.setValue(new Long(newPanelHeight));
		txtHeight.addPropertyChangeListener("value", this);

		txtWidth = new JFormattedTextField(paymentFormat);
		txtWidth.setValue(new Long(newPanelWidth));
		txtWidth.addPropertyChangeListener("value", this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHieght)
								.addComponent(lblWidth))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtWidth)
								.addComponent(txtHeight, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblpixels)
								.addComponent(lblInPixels))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addComponent(btnOk)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblpixels)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInPixels)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHieght))
					.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		newPanelHeight = ((Long) txtHeight.getValue()).intValue();
		newPanelWidth = ((Long) txtWidth.getValue()).intValue();
	}
	
	public int getPanelHeight(){
		return newPanelHeight;
	}
	
	public int getPanelWidth(){
		return newPanelWidth;
	}
}
