package graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AboutUs extends JDialog {
	private JButton exitButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			AboutUs dialog = new AboutUs();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutUs() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutUs.class.getResource("/resources/Taz-icon.png")));
		setTitle("About");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 450, 300);
		
		JTextArea txtrAuthorsLimpAlimonostony = new JTextArea();
		txtrAuthorsLimpAlimonostony.setEditable(false);
		txtrAuthorsLimpAlimonostony.setBackground(Color.LIGHT_GRAY);
		txtrAuthorsLimpAlimonostony.setForeground(Color.BLACK);
		txtrAuthorsLimpAlimonostony.setFont(new Font("Monospaced", Font.BOLD, 12));
		txtrAuthorsLimpAlimonostony.setText("Authors: Limp Alimonos,Tony Beh\r\nRealease Date: Feb 11,2020\r\nVersion: 1.0 Beta ");
		
		exitButton = new JButton("Done");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrAuthorsLimpAlimonostony, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(158, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(347, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addGap(30))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrAuthorsLimpAlimonostony, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
