package com.uml.contradiction.model.cclass;

import java.util.Set;
import java.util.List;

import com.uml.contradiction.model.Vertex;
import com.uml.contradiction.model.VertexType;
import com.uml.contradiction.model.common.PackageElement;
import com.uml.contradiction.model.common.Stereotype;

public class CClass implements Vertex {
	private String name;
	private PackageElement parentPackageElement;
	private Set<Stereotype> stereotypes;
	private Visibility visibility;	
	private boolean isAbstract;
	private String properties;
	private List<Attribute> attributes;
	private List<MMethod> methods;
	@SuppressWarnings("rawtypes")
	private Set templateParameters;
	private String requiredInterface;
	private List<CClass> nestedCClasses;
	
	public String getName() {
		return name;
	}
	
	public String getRequiredInterface() {
		return requiredInterface;
	}

	public void setRequiredInterface(String requiredInterface) {
		this.requiredInterface = requiredInterface;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Set<Stereotype> getStereotypes() {
		return stereotypes;
	}
	public void setStereotypes(Set<Stereotype> stereotypes) {
		this.stereotypes = stereotypes;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public boolean isAbstract() {
		return isAbstract;
	}
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public List<MMethod> getMethods() {
		return methods;
	}
	public void setMethods(List<MMethod> methods) {
		this.methods = methods;
	}
	public Set getTemplateParameters() {
		return templateParameters;
	}
	public void setTemplateParameters(Set templateParameters) {
		this.templateParameters = templateParameters;
	}
	public PackageElement getParentPackageElement() {
		return parentPackageElement;
	}
	public void setParentPackageElement(PackageElement parentPackageElement) {
		this.parentPackageElement = parentPackageElement;
	}
	public String getFullName(){
		String fullName = new String();
		PackageElement packageElement = parentPackageElement;
		while(packageElement != null){
			fullName = packageElement.getName() + "." + fullName;
			packageElement = packageElement.getParentPackageElement();
		}
		fullName = fullName + name;
		return fullName;
	}
	@Override
	public String toString() {
		return "CClass [name=" + name 
//				+ ", fullName=" + getFullName()
				+ ", package name=" + parentPackageElement.getName()
				+ ", stereotypes=" + stereotypes + ", visibility=" + visibility
				+ ", isAbstract=" + isAbstract + ", properties=" + properties
				+ ", attributes=" + attributes + ", methods=" + methods
				+ ", templateParameters=" + templateParameters
				+ ", requiredInterface=" + requiredInterface + "]";
	}

	@Override
	public VertexType getType() {
		return VertexType.CLASS;
	}
	
}
