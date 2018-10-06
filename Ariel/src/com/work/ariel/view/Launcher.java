package com.work.ariel.view;

import javax.swing.SwingUtilities;

import com.work.ariel.system.SystemConfig;
import com.work.ariel.view.frame.JWorkAreaFrame;

/**
 * The main launcher of the program.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class Launcher implements Runnable{
	private final SystemConfig systemConfig = SystemConfig.getInstance();
	
	private Launcher() {
		
	}
	
	@Override
	public void run() {
		systemConfig.load();
		new JWorkAreaFrame();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Launcher());
	}
}
