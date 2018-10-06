package com.work.ariel.view.menubar;

import javax.swing.JMenuBar;

import com.work.ariel.view.menu.JAboutMenu;
import com.work.ariel.view.menu.JFileMenu;
import com.work.ariel.view.menu.JPreferenceMenu;

public class JDefaultMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1502268509454315874L;
	
	private JFileMenu mnu_file;
	private JPreferenceMenu mnu_prefferences;
	private JAboutMenu mnu_about;
	
	public JDefaultMenuBar() {
		initialize();
	}
	
	public void initialize() {
		mnu_file = new JFileMenu();
		mnu_prefferences = new JPreferenceMenu();
		mnu_about = new JAboutMenu();

		add(mnu_file);
		add(mnu_prefferences);
		add(mnu_about);
	}
}
