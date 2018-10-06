package com.work.ariel.view.panel;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class JHintAreaPanel extends JPanel{
	
	private static final long serialVersionUID = 6801112539767495147L;
	
	private JTextArea txa_hint;
	
	public JHintAreaPanel() {
		initialize();
	}
	
	private void initialize() {
		txa_hint = new JTextArea(5, 20);
		
		setBorder(BorderFactory.createTitledBorder("Hint"));
		setPreferredSize(new Dimension(780, 100));
		
		add(txa_hint);
		
		setLayout();
	}
	
	private void setLayout() {
		SpringLayout layout = null;

		layout = new SpringLayout();

		setLayout(layout);
		
		layout.putConstraint(SpringLayout.WEST, txa_hint, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, txa_hint, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txa_hint, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, txa_hint, -10, SpringLayout.SOUTH, this);
	}

}
