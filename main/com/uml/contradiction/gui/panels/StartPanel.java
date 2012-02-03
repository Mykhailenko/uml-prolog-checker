package com.uml.contradiction.gui.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.uml.contradiction.gui.listeners.LoadXMIListener;
import com.uml.contradiction.gui.vocabularies.english.StartPanelVoc;

public class StartPanel extends JPanel {
	
	public StartPanel() {
		super();
		this.createGUI();
	}
	
	private void createGUI() {
		final JButton loadXMIBtn = new JButton(StartPanelVoc.getLabel("loadXMIBtn"));
		
		loadXMIBtn.addActionListener(new LoadXMIListener());
		
		this.add(loadXMIBtn);
		this.updateUI();
	}
}
