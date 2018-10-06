package com.work.ariel.view.panel;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class JDetailsPanel extends JPanel {
	private static final long serialVersionUID = 7879640073821330837L;

	private JDocumentDetailsPanel pnl_documentDetails;
	private JFTPDetailsPanel pnl_ftpDetails;

	public JDetailsPanel() {
		initialize();
	}
	
	private void initialize() {
		pnl_documentDetails = new JDocumentDetailsPanel();
		pnl_ftpDetails = new JFTPDetailsPanel();

		setPreferredSize(new Dimension(780, 200));
		
		add(pnl_documentDetails);
		add(pnl_ftpDetails);
		
		setLayout();
	}
	
	private void setLayout() {
		SpringLayout layout = new SpringLayout();

		setLayout(layout);
		
		layout.putConstraint(SpringLayout.WEST, pnl_documentDetails, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, pnl_ftpDetails, -5, SpringLayout.EAST, this);
	}

	public JDocumentDetailsPanel getPnl_documentDetails() {
		return pnl_documentDetails;
	}

	public JFTPDetailsPanel getPnl_ftpDetails() {
		return pnl_ftpDetails;
	}

}
