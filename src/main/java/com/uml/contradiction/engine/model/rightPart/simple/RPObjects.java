package com.uml.contradiction.engine.model.rightPart.simple;

import java.util.List;

import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.rightPart.QuantifierRightPart;
import com.uml.contradiction.model.object.ObjectGraph;

public class RPObjects implements QuantifierRightPart {

	@SuppressWarnings("rawtypes")
	@Override
	public List getSet(List<VariableValue> params) {
		return ObjectGraph.getObjects();
	}

}
