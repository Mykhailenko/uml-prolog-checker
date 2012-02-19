package com.uml.contradiction.gui.sceneries;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.Engine;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.gui.controllers.PanelsController;
import com.uml.contradiction.gui.models.DisplayedCriterion;
import com.uml.contradiction.gui.panels.ContradictionsPanel;
import com.uml.contradiction.gui.panels.VerificationResultsPanel;

public class StartCheckScenery {
	private static final Logger LOGGER = Logger.getRootLogger();
	
	public static void run(List<DefaultMutableTreeNode> nodes) {
		System.out.println("CheckScenery");
		ContradictionsPanel contrPanel = PanelsController.contradictionsPanel;
		VerificationResultsPanel resPanel = PanelsController.resultsPanel;
		
		DefaultMutableTreeNode newRoot = getSelectedTree(nodes);
		List<Criterion> selectedCriterions = getSelectedCriterions(newRoot);
		Map<Criterion, VerificationResult> results = new HashMap<Criterion, VerificationResult>();
		
		for(Criterion crit:selectedCriterions) {
			LOGGER.debug("checking criterion: " + crit);
			Engine engine = new Engine(crit);
			VerificationResult result = engine.verify();
			results.put(crit, result);
		}
		
		resPanel.setResults(results);
		resPanel.setSelectedDiagrams(newRoot);
		PanelsController.showPanel(resPanel);
		
		
		return;
	}
	
	public static DefaultMutableTreeNode getSelectedTree(List<DefaultMutableTreeNode> selectedNodes) {
		DefaultMutableTreeNode basicRoot = (DefaultMutableTreeNode) PanelsController.contradictionsPanel.getTree().getModel().getRoot();
		if(selectedNodes.contains(basicRoot))
			return basicRoot;
	
		DefaultMutableTreeNode newRoot = (DefaultMutableTreeNode) basicRoot.clone();
		for(int i = 0; i < basicRoot.getChildCount(); i++) {
			DefaultMutableTreeNode type = (DefaultMutableTreeNode) basicRoot.getChildAt(i);
			
			if(selectedNodes.contains(type)) {
				DefaultMutableTreeNode newType = (DefaultMutableTreeNode) type.clone();
				newRoot.add(newType);
				for(int j = 0; j < type.getChildCount(); j++) {
					DefaultMutableTreeNode creterion = (DefaultMutableTreeNode) type.getChildAt(j);
					newType.add((DefaultMutableTreeNode)creterion.clone());
				}
			}
			
			else {
				DefaultMutableTreeNode newType =  (DefaultMutableTreeNode) type.clone();
				
				boolean isTypeAdded = false;
				for(int j = 0; j < type.getChildCount(); j++) {
					DefaultMutableTreeNode creterion = (DefaultMutableTreeNode) type.getChildAt(j);
					
					if(selectedNodes.contains(creterion)) {
						if(!isTypeAdded) {
							newRoot.add(newType);
							isTypeAdded = true;
						}
						newType.add((MutableTreeNode) creterion.clone());
					}
				}
			}
		}
		
		return newRoot;
	}

	public static List<Criterion> getSelectedCriterions(DefaultMutableTreeNode root) {
		List<Criterion> res = new LinkedList<Criterion>();
		
		DefaultMutableTreeNode leaf = root.getFirstLeaf();
		do {
			res.add(((DisplayedCriterion)leaf.getUserObject()).getCriterion());
			leaf = leaf.getNextLeaf();
		} while(leaf != null);
		
		return res;
	}
}
