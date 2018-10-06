package com.work.ariel.view.panel;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.work.ariel.system.SystemConstants;

public class JDocumentDetailsPanel extends JPanel{
	private static final long serialVersionUID = 7217175411139634957L;
	
	private JLabel lbl_documentType;
	private JLabel lbl_teamName;
	private JLabel lbl_dbsVersion;
	private JLabel lbl_ticketNumber;
	
	private ButtonGroup documentTypeButtonGroup;
	private JRadioButton rbt_documentTypePAFA;
	private JRadioButton rbt_documentTypeTP;
	private JTextField txt_teamName;
	private JComboBox<String> cmb_dbsVersion;
	private JTextField txt_ticketNumber;
	
	public JDocumentDetailsPanel() {
		initialize();
	}
	
	private void initialize() {
		lbl_documentType = new JLabel("Document Type *");
		lbl_teamName = new JLabel("Team Name *");
		lbl_dbsVersion = new JLabel("DBS Version *");
		lbl_ticketNumber = new JLabel("Ticket Number *");
		
		documentTypeButtonGroup = new ButtonGroup();
		rbt_documentTypePAFA = new JRadioButton("PAFA");
		rbt_documentTypeTP = new JRadioButton("TP");
		txt_teamName = new JTextField();
		cmb_dbsVersion = new JComboBox<String>(new String[] {"5.0", "2.5"}); // TODO These values must eventually moved to an external file.
		txt_ticketNumber = new JTextField();
		
		txt_teamName.setToolTipText("Sample Tooltip"); // TODO Determine where to retrieve tooltips / what tooltips to use
		cmb_dbsVersion.setToolTipText("Sample Tooltip"); // TODO Determine where to retrieve tooltips / what tooltips to use
		txt_ticketNumber.setToolTipText("Sample Tooltip"); // TODO Determine where to retrieve tooltips / what tooltips to use
		
		setBorder(BorderFactory.createTitledBorder("Document Details"));
		setPreferredSize(new Dimension(300, 200));

		add(lbl_documentType);
		add(lbl_teamName);
		add(lbl_dbsVersion);
		add(lbl_ticketNumber);
		
		documentTypeButtonGroup.add(rbt_documentTypePAFA);
		documentTypeButtonGroup.add(rbt_documentTypeTP);
		
		add(rbt_documentTypePAFA);
		add(rbt_documentTypeTP);
		add(txt_teamName);
		add(cmb_dbsVersion);
		add(txt_ticketNumber);
		
		txt_teamName.setPreferredSize(new Dimension(120, 20));
		cmb_dbsVersion.setPreferredSize(new Dimension(120, 20));
		txt_ticketNumber.setPreferredSize(new Dimension(120, 20));
		
		setLayout();
	}
	
	private void setLayout() {
		SpringLayout layout = null;
		
		layout = new SpringLayout();

		setLayout(layout);

		layout.putConstraint(SpringLayout.WEST, lbl_documentType, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_documentType, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, lbl_teamName, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_teamName, 30, SpringLayout.NORTH, lbl_documentType);
		
		layout.putConstraint(SpringLayout.WEST, lbl_dbsVersion, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_dbsVersion, 30, SpringLayout.NORTH, lbl_teamName);
		
		layout.putConstraint(SpringLayout.WEST, lbl_ticketNumber, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_ticketNumber, 30, SpringLayout.NORTH, lbl_dbsVersion);

		layout.putConstraint(SpringLayout.EAST, rbt_documentTypeTP, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, rbt_documentTypeTP, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, rbt_documentTypePAFA, -10, SpringLayout.WEST, rbt_documentTypeTP);
		layout.putConstraint(SpringLayout.NORTH, rbt_documentTypePAFA, 10, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.EAST, txt_teamName, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txt_teamName, 30, SpringLayout.NORTH, rbt_documentTypePAFA);

		layout.putConstraint(SpringLayout.EAST, cmb_dbsVersion, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, cmb_dbsVersion, 30, SpringLayout.NORTH, txt_teamName);

		layout.putConstraint(SpringLayout.EAST, txt_ticketNumber, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txt_ticketNumber, 30, SpringLayout.NORTH, cmb_dbsVersion);
	}

	public String getSelectedDocumentType() {
		if(rbt_documentTypePAFA.isSelected()) {
			return SystemConstants.DOCUMENT_TYPE_PAFA;
		}else if(rbt_documentTypeTP.isSelected()){
			return SystemConstants.DOCUMENT_TYPE_TP;
		}
		
		return null;
	}
	
	public String getTeamName() {
		return txt_teamName.getText();
	}

	public String getDBSVersion() {
		return cmb_dbsVersion.getSelectedItem().toString();
	}

	public String getTicketNumber() {
		return txt_ticketNumber.getText();
	}
}
