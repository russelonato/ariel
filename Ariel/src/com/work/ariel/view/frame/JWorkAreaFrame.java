package com.work.ariel.view.frame;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.work.ariel.system.SystemInformation;
import com.work.ariel.view.menubar.JDefaultMenuBar;
import com.work.ariel.view.panel.JWorkAreaPanel;

public class JWorkAreaFrame extends JFrame {
	private static final long serialVersionUID = -4456833778674922734L;

	private JWorkAreaPanel pnl_workArea;
	private JDefaultMenuBar mbr_menuBar;

	public JWorkAreaFrame() {
		super(SystemInformation.SYSTEM_NAME);
		initialize();
	}

	private void initialize() {
		pnl_workArea = new JWorkAreaPanel();
		mbr_menuBar = new JDefaultMenuBar();

		setJMenuBar(mbr_menuBar);
		getContentPane().add(pnl_workArea);

		getRootPane().setDefaultButton((JButton) pnl_workArea.getComponent(2));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setResizable(false);
		setSize(new Dimension(800, 400));
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
