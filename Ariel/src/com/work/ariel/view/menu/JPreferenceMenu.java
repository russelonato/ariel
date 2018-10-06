package com.work.ariel.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.work.ariel.view.frame.JPreferencesFrame;

public class JPreferenceMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = 2441517292671854890L;
	
	private JMenuItem mni_configure;

	public JPreferenceMenu() {
		super("Preferences");
		initialize();
	}

	public void initialize() {
		mni_configure = new JMenuItem("Configure");

		add(mni_configure);

		mni_configure.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mni_configure) {
			new JPreferencesFrame();
		}
		
	}
}
