package com.uml.contradiction.engine.model.predicate;

import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.engine.model.Variable;
import com.uml.contradiction.engine.model.VariableValue;
import com.uml.contradiction.engine.model.predicate.exception.PredicatException;

public class BoundedPredicate implements Formula{
	private List<Variable> boundVariable = new LinkedList<Variable>();
	private boolean negative = false;
	private Predicate predicate;
	public boolean isNegative() {
		return this.negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

	public List<Variable> getBoundVariable() {
		return boundVariable;
	}

	public void setBoundVariable(List<Variable> boundVariable) {
		this.boundVariable = boundVariable;
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	@Override
	public boolean predict(List<VariableValue> variableValues) {
		List<Object> vars = new LinkedList<Object>();
		for(int i = 0; i < boundVariable.size(); ++i){
			vars.add(findVV(variableValues, boundVariable.get(i)));
		}
		boolean result = false;
		try {
			result = predicate.predict(vars);
		} catch (PredicatException e) {
		}
		if(negative){
			result = !result;
		}
		return result;
	}

	private Object findVV(List<VariableValue> list, Variable variable){
		for(VariableValue variableValue : list){
			if(variableValue.variable.equals(variable)){
				return variableValue.value;
			}
		}
		return null;
	}

}