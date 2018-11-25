package com.work.ariel.view.panel;

import static com.work.ariel.property.impl.StringPropertyHandler.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import com.work.ariel.property.impl.StringPropertyHandler;
import com.work.ariel.property.interfce.PropertyHandler;
import com.work.ariel.system.SystemConfig;

public class JPreferencesPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -3176042373722527234L;
	private final SystemConfig systemConfig = SystemConfig.getInstance();
	
	private final PropertyHandler props = StringPropertyHandler.getInstance();

	private JLabel lbl_templateTd;
	private JLabel lbl_templatePafa;
	private JLabel lbl_spacing;

	private JTextField txt_templateTp;
	private JTextField txt_templatePafa;
	private JTextField txt_spacing;

	private JButton btn_templateTp;
	private JButton btn_templatePafa;

	private JButton btn_save;
	private JButton btn_cancel;
	
	private JFileChooser csr_browseFolderLocation;

	public JPreferencesPanel() {
		initialize();
	}

	private void initialize() {
		lbl_templateTd = new JLabel(props.getProperty(TD));
		lbl_templatePafa = new JLabel(props.getProperty(PAFA));
		lbl_spacing = new JLabel(props.getProperty(SPACING));

		txt_templateTp = new JTextField((String) systemConfig.getConfig(SystemConfig.TEMPLATE_FILE_TP));
		txt_templatePafa = new JTextField((String) systemConfig.getConfig(SystemConfig.TEMPLATE_FILE_PAFA));
		txt_spacing = new JTextField((String) systemConfig.getConfig(SystemConfig.SPACING));

		btn_templateTp = new JButton(props.getProperty(BROWSE));
		btn_templatePafa = new JButton(props.getProperty(BROWSE));

		btn_save = new JButton(props.getProperty(SAVE));
		btn_cancel = new JButton(props.getProperty(CANCEL));
		
		csr_browseFolderLocation = new JFileChooser();
		
		add(lbl_templateTd);
		add(lbl_templatePafa);
		add(lbl_spacing);
		
		add(txt_templateTp);
		add(txt_templatePafa);
		add(txt_spacing);
		
		add(btn_templateTp);
		add(btn_templatePafa);
		
		add(btn_save);
		add(btn_cancel);
		
		btn_templateTp.setPreferredSize(new Dimension(80, 19));
		btn_templatePafa.setPreferredSize(new Dimension(80, 19));
		
		btn_templateTp.addActionListener(this);
		btn_templatePafa.addActionListener(this);
		btn_save.addActionListener(this);
		btn_cancel.addActionListener(this);
		
		csr_browseFolderLocation.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		setLayout();
	}
	
	private void setLayout() {
		SpringLayout layout = null;

		layout = new SpringLayout();
		
		setLayout(layout);
		
		layout.putConstraint(SpringLayout.WEST, lbl_templateTd, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_templateTd, 30, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, lbl_templatePafa, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_templatePafa, 30, SpringLayout.NORTH, lbl_templateTd);
		
		layout.putConstraint(SpringLayout.WEST, lbl_spacing, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, lbl_spacing, 30, SpringLayout.NORTH, lbl_templatePafa);

		layout.putConstraint(SpringLayout.WEST, txt_templateTp, 10, SpringLayout.EAST, lbl_templateTd);
		layout.putConstraint(SpringLayout.EAST, txt_templateTp, -10, SpringLayout.WEST, btn_templateTp);
		layout.putConstraint(SpringLayout.NORTH, txt_templateTp, 30, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, txt_templatePafa, 10, SpringLayout.EAST, lbl_templatePafa);
		layout.putConstraint(SpringLayout.EAST, txt_templatePafa, -10, SpringLayout.WEST, btn_templatePafa);
		layout.putConstraint(SpringLayout.NORTH, txt_templatePafa, 30, SpringLayout.NORTH, txt_templateTp);
		
		layout.putConstraint(SpringLayout.WEST, txt_spacing, 10, SpringLayout.EAST, lbl_spacing);
		layout.putConstraint(SpringLayout.EAST, txt_spacing, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txt_spacing, 30, SpringLayout.NORTH, txt_templatePafa);
		
		layout.putConstraint(SpringLayout.EAST, btn_templateTp, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, btn_templateTp, 30, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.EAST, btn_templatePafa, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, btn_templatePafa, 30, SpringLayout.NORTH, btn_templateTp);
		
		layout.putConstraint(SpringLayout.EAST, btn_save, -10, SpringLayout.WEST, btn_cancel);
		layout.putConstraint(SpringLayout.SOUTH, btn_save, -10, SpringLayout.SOUTH, this);

		layout.putConstraint(SpringLayout.EAST, btn_cancel, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, btn_cancel, -10, SpringLayout.SOUTH, this);
	}

	private void doBtn_templateTpIsClicked() {
		int dialogResult = csr_browseFolderLocation.showOpenDialog(this);
		
		if(dialogResult == JFileChooser.APPROVE_OPTION) {
			txt_templateTp.setText(csr_browseFolderLocation.getSelectedFile().getPath());
		}
	}
	
	private void doBtn_templatePafaIsClicked() {
		int dialogResult = csr_browseFolderLocation.showOpenDialog(this);
		
		if(dialogResult == JFileChooser.APPROVE_OPTION) {
			txt_templatePafa.setText(csr_browseFolderLocation.getSelectedFile().getPath());
		}
	}
	
	private void doBtn_saveIsClicked() {
		systemConfig.setConfig(SystemConfig.TEMPLATE_FILE_TP, txt_templateTp.getText());
		systemConfig.setConfig(SystemConfig.TEMPLATE_FILE_PAFA, txt_templatePafa.getText());
		systemConfig.setConfig(SystemConfig.SPACING, txt_spacing.getText());
		
		systemConfig.save();
		
		SwingUtilities.getWindowAncestor(this).dispose();
	}
	
	private void doBtn_CancelIsClicked() {
		SwingUtilities.getWindowAncestor(this).dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_templateTp) {
			doBtn_templateTpIsClicked();
		}else if(e.getSource() == btn_templatePafa) {
			doBtn_templatePafaIsClicked();
		}else if(e.getSource() == btn_save) {
			doBtn_saveIsClicked();
		}else if(e.getSource() == btn_cancel) {
			doBtn_CancelIsClicked();
		}
		
	}

}
