package graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import datamodel.Staff;
import mainengine.MainEngine;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.UIManager;

public class mixas extends JFrame {
	private JMenuItem importBttn;
	static MainEngine me = new MainEngine();

	private JMenuItem exportHtmlButn;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mixas frame = new mixas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mixas() {

		initComponents();
		createEvents();

	}

	private void createEvents() {
		importBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				OpenFile of = new OpenFile();
				String filePath = "";
				
				try {
					filePath =  of.PickMe();
					me.load(filePath);

				} catch (Exception ex) {

				}
				
				
				if(!filePath.equals("")) {
					if (me.hasExtractedStaff()) {

						JOptionPane.showMessageDialog(null,
								"Staff found!\nIf a name does not match a staff member please enter 0 as salary/hour.");
						
						Collection<Staff> extractedStaff = me.getExtractedStaff();
						
						Iterator<Staff> it = extractedStaff.iterator();
						
						while (it.hasNext()) {
							
							
							Staff currentStaff = it.next();
							
							boolean exet = false;
							
							while(exet == false) {
							
								String salary = JOptionPane.showInputDialog(null, "Salary for \""+currentStaff.getName() + "\": ");
								try {
									double dsalary = Double.parseDouble(salary);
									
									me.hire(currentStaff, dsalary);
									exet = true;
								} catch (Exception ex) {
									
									int selectedOption = JOptionPane.showConfirmDialog(null,
											"Illegal input. Do you want " + currentStaff.getName() + " to be discared?", "Choose",
											JOptionPane.YES_NO_OPTION);
									if (selectedOption == JOptionPane.YES_OPTION) {
										exet = true;
									}
								}
								
							}
							
						}

						me.clearExtractedStaff();

					} else {
						JOptionPane.showMessageDialog(null,
								"No data were found. Please make sure you are loading an xlsx file with the aprropriate format.");
					}
				}
				
			}
		});

		exportHtmlButn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (me.getNumberOfEmployees() == 0) {
					JOptionPane.showMessageDialog(null, "No data to print. Load an xlsx file with appropriate format");
				} else {
					Save saveFile = new Save();
					String filePath = saveFile.mixas();
					if (!filePath.equals("")) {
						me.printToFile(filePath);
						JOptionPane.showMessageDialog(null, "File created!");
					}
				}

			}
		});
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutUs about = new AboutUs();
				about.setModal(true);
				about.setVisible(true);
			}
		});

	}

	private void initComponents() {
		setTitle("\u03A6\u03BF\u03C5\u03C1\u03BD\u03BF\u03C2");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(mixas.class.getResource("/resources/m.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 400);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				mixas.class.getResource("/resources/84003374_10216044570092893_701322151747125248_n.jpg")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addComponent(lblNewLabel).addContainerGap(498, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addComponent(lblNewLabel).addContainerGap(357, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mainMenu = new JMenu("File");
		menuBar.add(mainMenu);

		importBttn = new JMenuItem("Import ");

		importBttn.setIcon(new ImageIcon(mixas.class.getResource("/resources/excel.png")));
		mainMenu.add(importBttn);

		exportHtmlButn = new JMenuItem("Export as HTML");
		exportHtmlButn.setIcon(new ImageIcon(mixas.class.getResource("/resources/html.png")));

		mainMenu.add(exportHtmlButn);
		
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("About");

		mnNewMenu.add(mntmNewMenuItem);
	}



}
