package com.work.ariel.view.label;

import javax.swing.JLabel;

/**
 * A custom JLabel that does custom made functions.
 * 
 * @author Onato, Russel Jan
 *
 */
public class JCustomLabel extends JLabel{
	private static final long serialVersionUID = 5992967628150061368L;
	
	public JCustomLabel(String text) {
		super(text);
	}
	
	/**
	 * Sets this component as required, appending a red "*" to its text.
	 * 
	 * @since Ariel v2.0
	 * @author Onato, Russel Jan
	 */
	public void setAsRequired() {
		super.setText("<html>" + getText() + " <font color='red'>*</font></html>");
	}

}
