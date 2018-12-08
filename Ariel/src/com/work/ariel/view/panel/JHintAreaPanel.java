package com.work.ariel.view.panel;

import static com.work.ariel.property.impl.StringPropertyHandler.HINT;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.work.ariel.property.impl.StringPropertyHandler;
import com.work.ariel.property.interfce.PropertyHandler;

public class JHintAreaPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 6801112539767495147L;

	private final PropertyHandler props = StringPropertyHandler.getInstance();

	private JTextArea txa_hint;

	public JHintAreaPanel() {
		initialize();
	}

	private void initialize() {
		txa_hint = new JTextArea(5, 20);

		setBorder(BorderFactory.createTitledBorder(props.getProperty(HINT)));
		setPreferredSize(new Dimension(780, 100));

		txa_hint.setEditable(false);

		add(txa_hint);

		txa_hint.addMouseListener(this);

		setLayout();
	}

	public void setHint() {
		List<String> hints = new ArrayList<String>();

		String hint = null;
		int index = 1;

		while (!(hint = StringPropertyHandler.getInstance().getProperty(HINT, String.valueOf(index))).equals("")) {
			hints.add(hint);
			index++;
		}

		txa_hint.setText(hints.get(new Random().nextInt(hints.size())));
	}

	private void setLayout() {
		SpringLayout layout = null;

		layout = new SpringLayout();

		setLayout(layout);

		layout.putConstraint(SpringLayout.WEST, txa_hint, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, txa_hint, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, txa_hint, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, txa_hint, -10, SpringLayout.SOUTH, this);
	}

	private void doTxa_hintIsClicked() {
		setHint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == txa_hint) {
			doTxa_hintIsClicked();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stubs
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
