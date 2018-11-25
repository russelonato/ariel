package com.work.ariel.view.panel;

import static com.work.ariel.property.impl.StringPropertyHandler.*;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.work.ariel.property.impl.StringPropertyHandler;
import com.work.ariel.property.interfce.PropertyHandler;
import com.work.ariel.system.SystemConstants;

public class JDocumentDetailsPanel extends JPanel{
	private static final long serialVersionUID = 7217175411139634957L;
	
	private final PropertyHandler props = StringPropertyHandler.getInstance();
	
	private JLabel lbl_documentType;
	private JLabel lbl_teamName;
	private JLabel lbl_dbsVersion;
	private JLabel lbl_ticketNumber;
	
	private ButtonGroup documentTypeButtonGroup;
	private JRadioButton rbt_documentTypePAFA;
	private JRadioButton rbt_documentTypeTD;
	private JTextField txt_teamName;
	private JComboBox<String> cmb_dbsVersion;
	private JTextField txt_ticketNumber;
	
	public JDocumentDetailsPanel() {
		initialize();
	}
	
	private void initialize() {
		lbl_documentType = new JLabel(props.getProperty(DOCUMENT_TYPE));
		lbl_teamName = new JLabel(props.getProperty(TEAM_NAME));
		lbl_dbsVersion = new JLabel(props.getProperty(DBS_VERSION));
		lbl_ticketNumber = new JLabel(props.getProperty(TICKET_NUMBER));
		
		documentTypeButtonGroup = new ButtonGroup();
		rbt_documentTypePAFA = new JRadioButton(props.getProperty(PAFA));
		rbt_documentTypeTD = new JRadioButton(props.getProperty(TD));
		txt_teamName = new JTextField();
		cmb_dbsVersion = new JComboBox<String>(props.getPropertyAsList(CMB_DB_VERSION).toArray(new String[0]));
		txt_ticketNumber = new JTextField();
		
		txt_teamName.setToolTipText(props.getProperty(TOOLIP, TEAM_NAME));
		cmb_dbsVersion.setToolTipText(props.getProperty(TOOLIP, DBS_VERSION)); 
		txt_ticketNumber.setToolTipText(props.getProperty(TOOLIP, TICKET_NUMBER)); 
		
		setBorder(BorderFactory.createTitledBorder(props.getProperty(DOCUMENT_DETAILS)));
		setPreferredSize(new Dimension(300, 200));

		add(lbl_documentType);
		add(lbl_teamName);
		add(lbl_dbsVersion);
		add(lbl_ticketNumber);
		
		documentTypeButtonGroup.add(rbt_documentTypePAFA);
		documentTypeButtonGroup.add(rbt_documentTypeTD);
		
		add(rbt_documentTypePAFA);
		add(rbt_documentTypeTD);
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

		layout.putConstraint(SpringLayout.EAST, rbt_documentTypeTD, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, rbt_documentTypeTD, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, rbt_documentTypePAFA, -10, SpringLayout.WEST, rbt_documentTypeTD);
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
		}else if(rbt_documentTypeTD.isSelected()){
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
