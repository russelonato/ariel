package com.work.ariel.view.panel;

import static com.work.ariel.property.impl.StringPropertyHandler.EXECUTE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.work.ariel.exception.SystemException;
import com.work.ariel.exception.ValidationException;
import com.work.ariel.property.impl.StringPropertyHandler;
import com.work.ariel.property.interfce.PropertyHandler;
import com.work.ariel.service.impl.BasicLaundrymat;
import com.work.ariel.service.interfce.ILaundrymat;
import com.work.ariel.system.SystemMessages;
import com.work.ariel.util.Logger;
import com.work.ariel.validation.WorkAreaPanelValidation;

public class JWorkAreaPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 9196892803414654034L;

	private final PropertyHandler props = StringPropertyHandler.getInstance();

	private JDetailsPanel pnl_details;
	private JHintAreaPanel pnl_hint;
	private JButton btn_execute;

	public JWorkAreaPanel() {
		initialize();
	}

	private void initialize() {

		pnl_details = new JDetailsPanel();
		pnl_hint = new JHintAreaPanel();
		btn_execute = new JButton(props.getProperty(EXECUTE));

		add(pnl_details);
		add(pnl_hint);
		add(btn_execute);

		pnl_hint.setHint();

		btn_execute.addActionListener(this);

		setLayout();
	}

	private void setLayout() {
		SpringLayout layout = null;

		layout = new SpringLayout();

		setLayout(layout);

		layout.putConstraint(SpringLayout.NORTH, pnl_details, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, pnl_hint, 10, SpringLayout.SOUTH, pnl_details);
		layout.putConstraint(SpringLayout.SOUTH, btn_execute, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, btn_execute, -10, SpringLayout.EAST, this);
	}

	private void doBtn_executeIsClicked() {
		ILaundrymat laundrymat = null;
		WorkAreaPanelValidation validation = null;

		laundrymat = new BasicLaundrymat();
		validation = new WorkAreaPanelValidation();

		String documentType = pnl_details.getPnl_documentDetails().getSelectedDocumentType();
		String teamName = pnl_details.getPnl_documentDetails().getTeamName();
		String dbsVersion = pnl_details.getPnl_documentDetails().getDBSVersion();
		String ticketNumber = pnl_details.getPnl_documentDetails().getTicketNumber();
		String folderLocation = pnl_details.getPnl_ftpDetails().getFolderLocation();
		String lparNumber = pnl_details.getPnl_ftpDetails().getLparNumber();
		String aspGroup = pnl_details.getPnl_ftpDetails().getAspGroup();
		String username = pnl_details.getPnl_ftpDetails().getUsername();
		String password = pnl_details.getPnl_ftpDetails().getPassword();

		pnl_hint.setHint();

		try {
			validation.validate(documentType, teamName, dbsVersion, ticketNumber, folderLocation, lparNumber, aspGroup,
					username, password);
			laundrymat.subtmitJob(documentType, teamName, dbsVersion, ticketNumber, folderLocation, lparNumber,
					aspGroup, username, password);

			int option = JOptionPane.showConfirmDialog(this, SystemMessages.EXECUTION_DONE, SystemMessages.EXECUTION_SUCCESSFUL, JOptionPane.INFORMATION_MESSAGE);
			if(option == JOptionPane.YES_OPTION) {
				String user = JOptionPane.showInputDialog("Username");
				String pass = JOptionPane.showInputDialog("Password");
				
				Logger.getInstance().submitLogs(user, pass);
			}
			
		} catch (ValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), SystemMessages.INVALID_INPUT,
					JOptionPane.ERROR_MESSAGE);
		} catch (SystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public JDetailsPanel getPnl_details() {
		return pnl_details;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_execute) {
			doBtn_executeIsClicked();
		}
	}
}
