package com.uml.contradiction.model.cclass;

import com.uml.contradiction.model.ocl.Constraint;

public class AssociationEnd {
	private String 			role;
	private Multiplicity 	multiplicity;
	private AggregationKind aggregationKind;
	private Visibility 		visibility;
	private Constraint		constraint;
	private boolean 		isDerived;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Multiplicity getMultiplicity() {
		return multiplicity;
	}
	public void setMultiplicity(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}
	public AggregationKind getAggregationKind() {
		return aggregationKind;
	}
	public void setAggregationKind(AggregationKind aggregationKind) {
		this.aggregationKind = aggregationKind;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public Constraint getConstraint() {
		return constraint;
	}
	public void setConstraint(Constraint constraint) {
		this.constraint = constraint;
	}
	public boolean isDerived() {
		return isDerived;
	}
	public void setDerived(boolean isDerived) {
		this.isDerived = isDerived;
	}
	
	
}