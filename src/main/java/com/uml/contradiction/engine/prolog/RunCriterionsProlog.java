package com.uml.contradiction.engine.prolog;

import java.util.Collections;
import java.util.List;

import com.uml.contradiction.engine.RunCriterions;
import com.uml.contradiction.engine.model.VerificationResult;
import com.uml.contradiction.engine.model.criteria.Criterion;
import com.uml.contradiction.engine.prolog.tranlatorforprolog.ClassTranslator;
import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.cclass.Dependency;
import com.uml.contradiction.model.cclass.Generalization;
import com.uml.contradiction.model.cclass.NaryAssociationClass;
import com.uml.contradiction.model.cclass.Realization;

public class RunCriterionsProlog implements RunCriterions {

	@Override
	public List<VerificationResult> runAll() {
		return null;
	}

	@Override
	public List<VerificationResult> run(List<Criterion> criterions) {
		List<CClass> classes = ClassGraph.getClasses();
		List<Association> associations = ClassGraph.getAssociations();
		List<Dependency> dependencies = ClassGraph.getDependencies();
		List<Generalization> generalizations = ClassGraph.getGeneralizations();
		List<NaryAssociationClass> naries = ClassGraph.getNaries();
		List<Realization> realizations = ClassGraph.getRealizations();
		
		ClassTranslator classTranslator = new ClassTranslator(classes,
				associations, dependencies, generalizations, naries,
				realizations);
		List<String> clasez =  classTranslator.translate();
		
		for(String s : clasez){
			System.out.println(s);
		}
		return Collections.EMPTY_LIST;
	}

}
