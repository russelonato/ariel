package com.work.ariel.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class JFileMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = -1348623369564216473L;
	
	private JMenuItem mni_exit;
	
	public JFileMenu() {
		super("File");
		initialize();
	}

	public void initialize() {
		mni_exit = new JMenuItem("Exit");

		add(mni_exit);

		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mni_exit) {
			dispatchEvent(new WindowEvent((JFrame) this.getParent(), WindowEvent.WINDOW_CLOSING));
		}
	}
}
