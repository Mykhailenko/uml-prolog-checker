package com.uml.contradiction.engine;

import java.util.List;

import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;

public interface RunCriterions {

	public abstract List<VerificationResult> runAll();

	public abstract List<VerificationResult> run(List<Criterion> criterions);

}