package com.work.ariel.view.menu;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.work.ariel.exception.SystemException;
import com.work.ariel.util.FileUtil;

public class JAboutMenu extends JMenu implements ActionListener {
	private static final long serialVersionUID = -6042571009239644612L;

	private JMenuItem mni_about;
	private JMenuItem mni_viewReadme;

	public JAboutMenu() {
		super("About");
		initialize();
	}

	public void initialize() {
		mni_about = new JMenuItem("About");
		mni_viewReadme = new JMenuItem("View Readme");

		add(mni_about);
		add(mni_viewReadme);

		mni_about.addActionListener(this);
		mni_viewReadme.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mni_about) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Version: v2.0.0\n");
			sb.append("Creators: GABRAMO\n");
			sb.append("Built using Java, Windows Batch Scripting\n");

			JOptionPane.showMessageDialog(this.getParent().getParent(), sb.toString(), "About", PLAIN_MESSAGE);
		} else if (e.getSource() == mni_viewReadme) {
			try {
				StringBuilder sb = new StringBuilder();

				for (String line : FileUtil.readFile(FileUtil.toFile("resource//readme.txt"))) {
					sb.append(line + "\n");
				}

				JOptionPane.showMessageDialog(this.getParent().getParent(), sb.toString(), "About", PLAIN_MESSAGE);

			} catch (SystemException ex) {
				ex.printStackTrace();
			}
		}
	}
}
