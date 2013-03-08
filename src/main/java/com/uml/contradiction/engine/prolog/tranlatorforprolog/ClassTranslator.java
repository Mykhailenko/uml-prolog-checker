package com.uml.contradiction.engine.prolog.tranlatorforprolog;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import com.uml.contradiction.model.cclass.Association;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.Dependency;
import com.uml.contradiction.model.cclass.Generalization;
import com.uml.contradiction.model.cclass.NaryAssociationClass;
import com.uml.contradiction.model.cclass.Realization;

public class ClassTranslator {
	private List<CClass> classes;
	private List<Association> associations;
	private List<Dependency> dependencies;
	private List<Generalization> generalizations;
	private List<NaryAssociationClass> naries;
	private List<Realization> realizations;
	public ClassTranslator(List<CClass> classes,
			List<Association> associations, List<Dependency> dependencies,
			List<Generalization> generalizations,
			List<NaryAssociationClass> naries, List<Realization> realizations) {
		super();
		this.classes = classes;
		this.associations = associations;
		this.dependencies = dependencies;
		this.generalizations = generalizations;
		this.naries = naries;
		this.realizations = realizations;
	}
	public List<String> translate() {
		List<String> result = new LinkedList<>();
		for(CClass cClass : classes){
			String s = MessageFormat.format("vclass( {0} ).", cClass.getFullName());
			result.add(s);
		}
		return result;
	}
	
}
