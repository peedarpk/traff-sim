package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import obj.Edge;
import obj.Path;

public class PathDialog extends JDialog {

	private static final long serialVersionUID = 3981809413901018708L;
	private JTextField txtPathName;
	private JTextField txtMaxSpeed;
	private JTextField txtVehicleMin;
	private JTextField txtEdges;
	private JTable table;
	DefaultTableModel model;
	ArrayList<Path> pathsList = new ArrayList<Path>();
	ArrayList<Edge> edgesList = new ArrayList<Edge>();

	public PathDialog(JFrame frame, ArrayList<Path> list, ArrayList<Edge> elist) {
		super(frame, true);
		setLocation(new Point(200, 100));
		setSize(new Dimension(800, 521));
		setTitle("Set Path");
		pathsList = list;
		edgesList = elist;
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(null);

		JPanel buttonPanel = new JPanel();

		JPanel bottomPanel = new JPanel();

		JPanel dataEntryPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(14)
																		.addComponent(dataEntryPanel,
																				GroupLayout.DEFAULT_SIZE, 766,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												tablePanel,
																												GroupLayout.DEFAULT_SIZE,
																												657,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												buttonPanel,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								bottomPanel,
																								GroupLayout.DEFAULT_SIZE,
																								768, Short.MAX_VALUE))))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 240,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(dataEntryPanel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));

		String columnNames[] = { "Name", "Edges", "Max Speed", "Vehicle/Min" };
		model = new DefaultTableModel(columnNames, 0);

		table = new JTable(model);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setBackground(Color.WHITE);
		table.setBorder(null);
		table.setPreferredScrollableViewportSize(new Dimension(650, 170));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		tablePanel.add(scrollPane);

		JLabel lblName = new JLabel("Name :");

		JLabel lblEdges = new JLabel("Edges :");

		JLabel lblMaxSpeed = new JLabel("Max Speed :");

		JLabel lblVehiclemin = new JLabel("Vehicle/min :");

		txtPathName = new JTextField();
		txtPathName.setColumns(10);

		txtMaxSpeed = new JTextField();
		txtMaxSpeed.setColumns(10);

		txtVehicleMin = new JTextField();
		txtVehicleMin.setColumns(10);

		txtEdges = new JTextField();
		txtEdges.setColumns(10);
		GroupLayout gl_dataEntryPanel = new GroupLayout(dataEntryPanel);
		gl_dataEntryPanel.setHorizontalGroup(gl_dataEntryPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_dataEntryPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_dataEntryPanel.createParallelGroup(Alignment.LEADING).addComponent(lblName)
										.addComponent(lblEdges).addComponent(lblMaxSpeed).addComponent(lblVehiclemin))
						.addGap(49)
						.addGroup(
								gl_dataEntryPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(txtVehicleMin, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
										.addGroup(
												gl_dataEntryPanel
														.createParallelGroup(Alignment.TRAILING)
														.addComponent(txtMaxSpeed)
														.addComponent(txtEdges, Alignment.LEADING)
														.addComponent(txtPathName, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)))
						.addContainerGap(117, GroupLayout.PREFERRED_SIZE)));
		gl_dataEntryPanel.setVerticalGroup(gl_dataEntryPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_dataEntryPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_dataEntryPanel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblName)
										.addComponent(txtPathName, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_dataEntryPanel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEdges)
										.addComponent(txtEdges, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_dataEntryPanel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblMaxSpeed)
										.addComponent(txtMaxSpeed, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_dataEntryPanel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtVehicleMin, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVehiclemin)).addGap(40)));
		dataEntryPanel.setLayout(gl_dataEntryPanel);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addValuesToTable();
				txtPathName.setText("");
				txtEdges.setText("");
				txtMaxSpeed.setText("");
				txtVehicleMin.setText("");
			}
		});

		JButton btnRemove = new JButton("Delete");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeRows(table.getSelectedRow());
				txtPathName.setText("");
				txtEdges.setText("");
				txtMaxSpeed.setText("");
				txtVehicleMin.setText("");
			}
		});
		GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
		gl_buttonPanel.setHorizontalGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_buttonPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_buttonPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(btnRemove, Alignment.TRAILING)
										.addComponent(btnAdd, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
		gl_buttonPanel.setVerticalGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_buttonPanel.createSequentialGroup().addGap(49).addComponent(btnAdd)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnRemove)
						.addContainerGap(55, Short.MAX_VALUE)));
		buttonPanel.setLayout(gl_buttonPanel);

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getValuesFromTable();
				setVisible(false);
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_bottomPanel.createSequentialGroup().addContainerGap(359, Short.MAX_VALUE).addComponent(btnCancel)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnApply).addContainerGap()));
		gl_bottomPanel.setVerticalGroup(gl_bottomPanel.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_bottomPanel
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								gl_bottomPanel.createParallelGroup(Alignment.BASELINE).addComponent(btnApply)
										.addComponent(btnCancel)).addContainerGap()));
		bottomPanel.setLayout(gl_bottomPanel);
		getContentPane().setLayout(groupLayout);
	}

	public void addValuesToTable() {

		model.addRow(new Object[] { txtPathName.getText(), txtEdges.getText(), txtMaxSpeed.getText(),
				txtVehicleMin.getText() });

	}

	public void removeRows(int row) {
		model.removeRow(row);
	}

	public void getValuesFromTable() {
		int rows = table.getRowCount();
		int cols = table.getColumnCount();

		for (int i = 0; i < rows; i++) {
			ArrayList<Edge> list = getEdgesListFromTable(i);
			String pName = (String) table.getModel().getValueAt(i, 0);
			int maxSpeed = new Integer (table.getModel().getValueAt(i, 2).toString());
			int vPerMin =  new Integer(table.getModel().getValueAt(i, 3).toString());
			Path path = new Path(pName, maxSpeed, vPerMin, list);
			
			pathsList.add(path);
		}
	}

	public ArrayList<Edge> getEdgesListFromTable(int row) {

		Map<String, Edge> edgeMap = new HashMap<String, Edge>();
		for (Edge e : edgesList) {
			edgeMap.put(e.getName(), e);
		}
		String edges[] = ((String) table.getModel().getValueAt(row, 1)).split(",");

		ArrayList<Edge> eList = new ArrayList<Edge>();

		for (int x = 0; x < edges.length; x++) {
			eList.add(edgeMap.get(edges[x]));
		}

		return eList;
	}
}
