package com.uml.contradiction.engine.model.mapping;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uml.contradiction.engine.model.mapping.exception.MappingException;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.ClassGraph;
import com.uml.contradiction.model.sequence.Message;

public class TargetMessageMapping implements Mapping {
	private static final Logger LOGGER = Logger.getRootLogger();
	@Override
	public List map(List list) throws MappingException {
		assert list != null;
		assert list.size() == 1;
		Object element = list.get(0);
		if(element instanceof Message){
			Message message = (Message) element;
			String className = message.getRecieveEvent().getCovered().getClassName();
			CClass cls = ClassGraph.findClassByName(className);
			if(cls == null){
				return null;
			}else{
				List<CClass> result = new LinkedList<CClass>();
				result.add(cls);
				return result;
			}
		}else{
			LOGGER.error("Unexpected type: " + element.getClass().toString());
			throw new MappingException("Unexpected type: " + element.getClass().toString());
		}
	}

}
