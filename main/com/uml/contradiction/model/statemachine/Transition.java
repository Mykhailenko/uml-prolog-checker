package com.uml.contradiction.model.statemachine;

import java.util.List;

import com.uml.contradiction.model.Vertex;

public class Transition {
	private Vertex target;
	private Vertex source;
	private TransitionKind kind;
	private List<Trigger> triggers;
	private Guard guard;
	public Vertex getTarget() {
		return target;
	}
	public void setTarget(Vertex target) {
		this.target = target;
	}
	public Vertex getSource() {
		return source;
	}
	public void setSource(Vertex source) {
		this.source = source;
	}
	public TransitionKind getKind() {
		return kind;
	}
	public void setKind(TransitionKind kind) {
		this.kind = kind;
	}
	public List<Trigger> getTriggers() {
		return triggers;
	}
	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}
	public Guard getGuard() {
		return guard;
	}
	public void setGuard(Guard guard) {
		this.guard = guard;
	}
	
}