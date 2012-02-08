package com.uml.contradiction.engine.model.rightPart.simple;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.diagram.ClassDiagram;
import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.engine.model.rightPart.SimpleRightPart;
import com.uml.contradiction.model.cclass.AggregationKind;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.AssociationEnd;

public class RPCompositions extends SimpleRightPart {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getSet(List<VariableValue> params) throws MappingException {
		List<Association> associations = ClassDiagram.getAssociations();
		List result = new LinkedList<Association>();
		
		for(int i = 0; i < associations.size(); i++) {
			Association ass = associations.get(i);
			AssociationEnd end1 = ass.getEnd1();
			AssociationEnd end2 = ass.getEnd2();
			if(end1.getAggregationKind() == AggregationKind.COMPOSITE ||
			   end2.getAggregationKind() == AggregationKind.COMPOSITE) {
				result.add(ass);
			}
		}
		
		return result;
	}

}
