package com.work.ariel.view.panel;

import static com.work.ariel.property.impl.StringPropertyHandler.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.work.ariel.property.impl.StringPropertyHandler;
import com.work.ariel.property.interfce.PropertyHandler;

public class JFTPDetailsPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -7161098503952761358L;
	
	private final PropertyHandler props = StringPropertyHandler.getInstance();
	
	private JLabel lbl_folderLocation;
	private JLabel lbl_lparNumber;
	private JLabel lbl_envAspGroup;
	private JLabel lbl_username;
	private JLabel lbl_password;
	
	private JTextField txt_folderLocation;
	private JButton btn_browseFolderLocation;
	private JTextField txt_lparNumber;
	private JTextField txt_aspGroup;
	private JTextField txt_username;
	private JPasswordField txt_password;
	
	private JFileChooser csr_browseFolderLocation;
	
	public JFTPDetailsPanel() {
		initialize();
	}
	
	private void initialize() {
		lbl_folderLocation = new JLabel(props.getProperty(FOLDER_LOCATION));
		lbl_lparNumber = new JLabel(props.getProperty(LPAR_NUMBER));
		lbl_envAspGroup = new JLabel(props.getProperty(ENV_ASP_GROUP));
		lbl_username = new JLabel(props.getProperty(USERNAME));
		lbl_password = new JLabel(props.getProperty(PASSWORD));
		
		txt_folderLocation = new JTextField();
		btn_browseFolderLocation = new JButton(props.getProperty(BROWSE));
		txt_lparNumber = new JTextField();
		txt_aspGroup = new JTextField();
		txt_username = new JTextField();
		txt_password = new JPasswordField();
		
		txt_folderLocation.setToolTipText(props.getProperty(TOOLIP, FOLDER_LOCATION));
		txt_lparNumber.setToolTipText(props.getProperty(TOOLIP, LPAR_NUMBER));
		txt_aspGroup.setToolTipText(props.getProperty(TOOLIP, ENV_ASP_GROUP));
		txt_username.setToolTipText(props.getProperty(TOOLIP, USERNAME));
		txt_password.setToolTipText(props.getProperty(TOOLIP, PASSWORD));
		
		csr_browseFolderLocation = new JFileChooser();
		
		setBorder(BorderFactory.createTitledBorder(props.getProperty(FTP_DETAILS)));
		setPreferredSize(new Dimension(460, 200));

		add(lbl_folderLocation);
		add(lbl_lparNumber);
		add(lbl_envAspGroup);
		add(lbl_username);
		add(lbl_password);
		
		add(txt_folderLocation);
		add(btn_browseFolderLocation);
		add(txt_lparNumber);
		add(txt_aspGroup);
		add(txt_username);
		add(txt_password);
		
		txt_folderLocation.setPreferredSize(new Dimension(200, 20));
		btn_browseFolderLocation.setPreferredSize(new Dimension(80, 19));
		txt_lparNumber.setPreferredSize(new Dimension(280, 20));
		txt_aspGroup.setPreferredSize(new Dimension(280, 20));
		txt_username.setPreferredSize(new Dimension(280, 20));
		txt_password.setPreferredSize(new Dimension(280, 20));
		
		btn_browseFolderLocation.addActionListener(this);
		
		csr_browseFolderLocation.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		setLayout();
	}
	
	private void setLayout() {
		SpringLayout layout = null;

		layout = new SpringLayout();

		setLayout(layout);

		layout.putConstraint(SpringLayout.WEST, lbl_folderLocation, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_folderLocation, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, lbl_lparNumber, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_lparNumber, 30, SpringLayout.NORTH, lbl_folderLocation);
		
		layout.putConstraint(SpringLayout.WEST, lbl_envAspGroup, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_envAspGroup, 30, SpringLayout.NORTH, lbl_lparNumber);
		
		layout.putConstraint(SpringLayout.WEST, lbl_username, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_username, 30, SpringLayout.NORTH, lbl_envAspGroup);
		
		layout.putConstraint(SpringLayout.WEST, lbl_password, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_password, 30, SpringLayout.NORTH, lbl_username);
		
		layout.putConstraint(SpringLayout.EAST, btn_browseFolderLocation, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, btn_browseFolderLocation, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, txt_folderLocation, 0, SpringLayout.WEST, btn_browseFolderLocation);
		layout.putConstraint(SpringLayout.NORTH, txt_folderLocation, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, txt_lparNumber, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txt_lparNumber, 30, SpringLayout.NORTH, btn_browseFolderLocation);
		
		layout.putConstraint(SpringLayout.EAST, txt_aspGroup, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txt_aspGroup, 30, SpringLayout.NORTH, txt_lparNumber);
		
		layout.putConstraint(SpringLayout.EAST, txt_username, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txt_username, 30, SpringLayout.NORTH, txt_aspGroup);
		
		layout.putConstraint(SpringLayout.EAST, txt_password, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txt_password, 30, SpringLayout.NORTH, txt_username);
	}
	
	private void doBtn_browseFolderLocationIsClicked() {
		int dialogResult = csr_browseFolderLocation.showOpenDialog(this);
		
		if(dialogResult == JFileChooser.APPROVE_OPTION) {
			txt_folderLocation.setText(csr_browseFolderLocation.getSelectedFile().getPath());
		}
	}

	public String getFolderLocation() {
		return txt_folderLocation.getText();
	}

	public String getLparNumber() {
		return txt_lparNumber.getText();
	}

	public String getAspGroup() {
		return txt_aspGroup.getText();
	}

	public String getUsername() {
		return txt_username.getText();
	}

	public String getPassword() {
		return String.valueOf(txt_password.getPassword());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_browseFolderLocation) {
			doBtn_browseFolderLocationIsClicked();
		}
		
	}
	
}
