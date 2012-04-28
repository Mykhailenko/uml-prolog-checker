package com.uml.contradiction.gui.panels;



import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.model.criteria.CriterionSuite;
import com.uml.contradiction.engine.model.criteria.CriterionTypeSuite;
import com.uml.contradiction.gui.Images;
import com.uml.contradiction.gui.components.CheckTreeManager;
import com.uml.contradiction.gui.listeners.ContrTreeListener;
import com.uml.contradiction.gui.listeners.StartCheckListener;
import com.uml.contradiction.gui.models.DiagramForChoise;
import com.uml.contradiction.gui.models.DisplayedCriterion;
import com.uml.contradiction.gui.models.DisplayedCriterionType;
import com.uml.contradiction.gui.vocabularies.english.DiagrPanelVoc;

public class ContradictionsPanel extends JPanel{
	private final JButton startBut = new JButton("Verify");
	private final JButton backBut = new JButton("<< Back");
	private final JTree tree = new JTree();
	private final JTextArea description = new JTextArea();
	private CheckTreeManager checkTreeManager;
	private final JLabel imgLbl = new JLabel();
	
	public ContradictionsPanel() {
		super();
		createGUI();
	}
	
	private void createGUI() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Criterions");
		DefaultTreeModel model = new DefaultTreeModel(root);
		
		List<DisplayedCriterion> criterions = CriterionSuite.getDisplayedCriterions();
		List<DisplayedCriterionType> types = CriterionTypeSuite.getAllDisplayedCriterions();
		
		for(DisplayedCriterionType type:types) {
			DefaultMutableTreeNode typeNode = new DefaultMutableTreeNode(type);
			root.add(typeNode);

			for(DisplayedCriterion crit:criterions){
				if(crit.getType().equals(type.getType())){
					typeNode.add(new DefaultMutableTreeNode(crit));
				}
			}
		}
		
		tree.addTreeSelectionListener(new ContrTreeListener());
		
		tree.setModel(model);
		this.checkTreeManager = new CheckTreeManager(tree);
		
		JLabel selectLabel = new JLabel("Contradictions:");
		selectLabel.setBounds(10, 0, 400, 20);
		this.add(selectLabel);
		
		JScrollPane treePanel = new JScrollPane(tree);
		JScrollPane descriptionPanel = new JScrollPane(description);
		description.setEditable(false);
		description.setOpaque(false);
		descriptionPanel.setBorder(null);
		description.setBorder(null);
		descriptionPanel.setOpaque(false);
		startBut.addActionListener(new StartCheckListener());
		
		imgLbl.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		imgLbl.setBackground(Color.white);
		imgLbl.setHorizontalAlignment(JLabel.CENTER);
		imgLbl.setOpaque(true);
		
		
		
		this.setLayout(null);
		treePanel.setBounds(10, 20, 290, 480);
		JLabel desLabel = new JLabel("Description:");
		desLabel.setBounds(310, 0, 400, 20);
		this.add(desLabel);
		descriptionPanel.setBounds(310, 20, 400, 120);
		
		JLabel imgLabel = new JLabel("Image:");
		imgLabel.setBounds(310, 140, 400, 20);
		this.add(imgLabel);
		imgLbl.setBounds(310, 160, 400, 340);
		startBut.setBounds(10, 510, 100, 25);
		this.add(treePanel);
		this.add(descriptionPanel);
		this.add(startBut);
		this.add(imgLbl);
		
		this.updateUI();
		this.repaint();
	}

	
	public void showDescription(Object object) {
		
		if(object instanceof DisplayedCriterion) {
			this.description.setText(((DisplayedCriterion)object).getDesctiption());
			this.description.repaint();
			this.description.updateUI();
			
			this.imgLbl.setIcon(((DisplayedCriterion)object).getImg());
			this.imgLbl.updateUI();
			this.imgLbl.repaint();
			
			this.updateUI();
		}
		else {
			this.description.setText("");
			this.description.repaint();
			this.description.updateUI();
			this.imgLbl.setIcon(null);
			this.imgLbl.updateUI();
			this.imgLbl.repaint();
			this.updateUI();			
		}
	}
	
	public List<DefaultMutableTreeNode> getSelectedNodes() {
		List<DefaultMutableTreeNode> res = new LinkedList<DefaultMutableTreeNode>();
		TreePath[] paths = checkTreeManager.getSelectionModel().getSelectionPaths(); 

		if(paths == null) {
			return res;
		}

		for(TreePath path:paths) {
			res.add(((DefaultMutableTreeNode)path.getLastPathComponent()));
		}
		
		return res;
	}
	
	public JTree getTree() {
		return tree;
	}
	
}
