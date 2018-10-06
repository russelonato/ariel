package com.work.ariel.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JAboutMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = -6042571009239644612L;
	
	private JMenuItem mni_about;

	public JAboutMenu() {
		super("About");
		initialize();
	}

	public void initialize() {
		mni_about = new JMenuItem("About");

		add(mni_about);

		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mni_about) {
			JOptionPane.showMessageDialog(this, "A little bit of information", "About", JOptionPane.OK_OPTION);
		}
	}
}
