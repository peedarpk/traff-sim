package ui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import obj.Edge;
import obj.Node;
import obj.Path;
import util.XMLBuilder;
import util.XMLReader;

public class Main extends JFrame {
	int ediorPanelWidth;
	int editorPanelHeight;

	MapEditor mapEditor = new MapEditor();
	Simulator simulator = new Simulator();
	JPanel mapEditorBase = new JPanel();
	JScrollPane mapEditorBaseScroll = new JScrollPane();
	ArrayList<Path> pathsList = new ArrayList<Path>();
	ArrayList<Edge> edgesList = new ArrayList<Edge>();
	ArrayList<Node> nodesList = new ArrayList<Node>();

	public Main() {
		setTitle("traff-sim");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mapEditorBase.setLayout(new BorderLayout(0, 0));
		mapEditorBase.add(mapEditorBaseScroll);
		mapEditorBaseScroll.add(mapEditor);
		mapEditorBaseScroll.setViewportView(mapEditor);
		mapEditor.setPreferredSize(new Dimension(1200, 600));
		mapEditor.setData(edgesList, nodesList);
		final PathDialog pathDialog = new PathDialog(this, pathsList, edgesList);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		toolBar.setMargin(new Insets(0, 4, 0, 4));

		JSeparator separator = new JSeparator();

		JPanel mainPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
				.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE).addGap(26)));
		mainPanel.setLayout(new BorderLayout(0, 0));

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				JTabbedPane pane = (JTabbedPane) evt.getSource();

				int sel = pane.getSelectedIndex();

			}
		});
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addTab("Map Editor", mapEditorBase);
		tabbedPane.addTab("Simulator", simulator);
		simulator.setData(edgesList, nodesList, pathsList);
		mainPanel.add(tabbedPane);

		JButton btnNewFile = new JButton("");
		btnNewFile.setSize(new Dimension(20, 0));
		btnNewFile.setIcon(new ImageIcon(Main.class.getResource("/icons/icons22x22/document-new.png")));
		toolBar.add(btnNewFile);

		JButton button = new JButton("");
		button.setToolTipText("Open...");
		button.setIcon(new ImageIcon(Main.class.getResource("/icons/icons22x22/document-open.png")));
		button.setName("btnOpenFile");
		toolBar.add(button);

		JButton btnSaveFile = new JButton("");
		btnSaveFile.setToolTipText("Save");
		btnSaveFile.setIcon(new ImageIcon(Main.class.getResource("/icons/icons22x22/document-save.png")));
		toolBar.add(btnSaveFile);
		getContentPane().setLayout(groupLayout);

		toolBar.addSeparator(new Dimension(20, 26));

		JButton btnPause = new JButton("");
		btnPause.setToolTipText("Pause");
		btnPause.setIcon(new ImageIcon(Main.class.getResource("/icons/icons22x22/media-playback-pause.png")));
		toolBar.add(btnPause);

		JButton btnRun = new JButton("");
		btnRun.setToolTipText("Play");
		btnRun.setIcon(new ImageIcon(Main.class.getResource("/icons/icons22x22/media-playback-start.png")));
		toolBar.add(btnRun);

		JButton btnStop = new JButton("");
		btnStop.setToolTipText("Stop");
		btnStop.setIcon(new ImageIcon(Main.class.getResource("/icons/icons22x22/process-stop.png")));
		toolBar.add(btnStop);

		toolBar.addSeparator(new Dimension(30, 26));

		JButton btnHelp = new JButton("");
		btnHelp.setToolTipText("Help");
		btnHelp.setIcon(new ImageIcon(Main.class.getResource("/icons/icons22x22/help-browser.png")));
		toolBar.add(btnHelp);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New ");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mntmNew.setIcon(new ImageIcon(Main.class.getResource("/icons/icons16x16/document-new.png")));
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("traff-sim files", "xml");
				fileopen.addChoosableFileFilter(filter);

				int ret = fileopen.showDialog(null, "Open");

				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileopen.getSelectedFile();
					System.out.println(file);
					new XMLReader(nodesList, pathsList, edgesList,file);
				}
				
			}
		});
		mntmOpen.setIcon(new ImageIcon(Main.class.getResource("/icons/icons16x16/document-open.png")));
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("traff-sim files", "xml");
				fileopen.addChoosableFileFilter(filter);

				int ret = fileopen.showDialog(null, "Save");

				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileopen.getSelectedFile();
					System.out.println(file);
					new XMLBuilder(nodesList, pathsList, edgesList,file);
				}

			}
		});

		mntmSave.setIcon(new ImageIcon(Main.class.getResource("/icons/icons16x16/document-save.png")));
		mnFile.add(mntmSave);

		mnFile.addSeparator();
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmSetPath = new JMenuItem("Set Path");
		mntmSetPath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pathDialog.setVisible(true);

			}
		});
		mnEdit.add(mntmSetPath);

		JMenu mnRun = new JMenu("Run");
		menuBar.add(mnRun);

		JMenu mnOutput = new JMenu("Output");
		menuBar.add(mnOutput);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		setSize(new Dimension(1024, 768));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setMapEditorProperties(NewPanelDialog newPanelDialog) {
		mapEditor.setPanelWidth(newPanelDialog.getPanelWidth());
		mapEditor.setPanelHeight(newPanelDialog.getPanelHeight());

		mapEditor.repaint();
	}

	public static void main(String[] args) {
		Main ui = new Main();
	}
}
