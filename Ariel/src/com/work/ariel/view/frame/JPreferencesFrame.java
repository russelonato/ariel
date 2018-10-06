package com.work.ariel.view.frame;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.work.ariel.view.panel.JPreferencesPanel;

public class JPreferencesFrame extends JFrame{
	private static final long serialVersionUID = -6289873246832120065L;
	
	private JPreferencesPanel pnl_preferences;
	
	public JPreferencesFrame() {
		super("Preferences");
		initialize();
	}
	
	private void initialize() {
		pnl_preferences = new JPreferencesPanel();
		
		add(pnl_preferences);

		pack();
		setResizable(false);
		setSize(new Dimension(275, 200));
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
