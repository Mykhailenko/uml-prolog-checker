package com.uml.contradiction.converter.core.sequence;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.uml.contradiction.converter.core.CoreParserImpl;
import com.uml.contradiction.converter.core.ParsersTool;
import com.uml.contradiction.converter.core.classes.CommonClDiagrHelper;
import com.uml.contradiction.model.cclass.CClass;
import com.uml.contradiction.model.cclass.MMethod;
import com.uml.contradiction.model.cclass.Parameter;
import com.uml.contradiction.model.common.*;
import com.uml.contradiction.model.sequence.*;

public class SequenceParsHelper {
	private static final Logger logger = Logger.getLogger(SequenceParsHelper.class);
	
	private Map<String, Interaction> interactionsFrWithId;
	private Map<String, LifeLine> lifelinesWithId;
	private Map<String, LifeLine> fragmentsWithLifeln;
	private Map<String, Message> messagesWithId;
	private Map<String, String> referencesOnClassId;
	
	public SequenceParsHelper(Map<String, Interaction> interactionsFrWithId,
			Map<String, LifeLine> lifelinesWithId,
			Map<String, LifeLine> fragmentsWithLifeln,
			Map<String, Message> messagesWithId) {
		super();
		this.interactionsFrWithId = interactionsFrWithId;
		this.lifelinesWithId = lifelinesWithId;
		this.fragmentsWithLifeln = fragmentsWithLifeln;
		this.messagesWithId = messagesWithId;
		
		referencesOnClassId = new LinkedHashMap<String, String>();
	}
	
	public LifeLine parseLifeline(Element lifeLine) {
		LifeLine lifeln = new LifeLine();
		CClass referClass;
		String lifelnName = lifeLine.getAttribute("name");
		if(!lifelnName.equals(""))
//			lifeln.setName(lifelnName);
			System.out.println("What a fack&&&");
		//������� �����, �� ������� ��������� lifeline
		if(lifeLine.hasAttribute("represents")){
			String idOwnAttr = lifeLine.getAttribute("represents");
			if(!idOwnAttr.equals("")){
				String refOnClass = referencesOnClassId.get(idOwnAttr);
				if(refOnClass != null){
					referClass = ParsersTool.getInstanceClassParser().getClassesWithId().get(refOnClass);
					if(referClass != null)
						lifeln.setcClass(referClass);
					else
						logger.info("reference on unknown cclass");
				}
			}
		}
				
		return lifeln;
	}
	
	public void parseAllLifelines(Element frameEl) {
		
		NodeList ownedAttrs = frameEl.getElementsByTagName("ownedAttribute");
		//�������� � ���������� �������� ��������� ����� �� ������
		for (int k = 0; k < ownedAttrs.getLength(); k++) {
			Element ownedAttrEl = (Element)ownedAttrs.item(k);			
			if(ownedAttrEl.getParentNode() == frameEl)
			{
				String idownAtr = ownedAttrEl.getAttribute("xmi:id");
				String refOnClass = ownedAttrEl.getAttribute("type");
				if(!refOnClass.equals(""))
					referencesOnClassId.put(idownAtr, refOnClass);
			}
		}		
		NodeList includedLifelines = frameEl.getElementsByTagName("lifeline");
		//�������� � ���������� ���������� �������
		for (int k = 0; k < includedLifelines.getLength(); k++) {
			Element lifelineEl = (Element)includedLifelines.item(k);
			
				//�������� ��� ����� Lifeline
			if(lifelineEl.getAttribute("xmi:type").equals("uml:Lifeline"))
			{
				//�������� LifeLine
				LifeLine curLifeLine = parseLifeline(lifelineEl);
				lifelinesWithId.put(lifelineEl.getAttribute("xmi:id"), curLifeLine);				
			}			
		}
	}
	
	//������ ���������� - ������������� ����� ����� lifeline  � message
	public void parseAllInlaidEvents(Element frameEl) {
		
		NodeList events = frameEl.getElementsByTagName("fragment");
		//�������� � ���������� ����������� Events
		for (int k = 0; k < events.getLength(); k++) {
			Element eventEl = (Element)events.item(k);
							
			//�������� cc���� �� lifeline
			String covd = eventEl.getAttribute("covered");
			LifeLine curLfln = lifelinesWithId.get(covd);
			if(curLfln == null)
				logger.error("There are no lifeline for fragment with id: " + covd);
			else{
				fragmentsWithLifeln.put(eventEl.getAttribute("xmi:id"), curLfln);
			}						
		}
	}
	
	public Message parseMessage(Element messElem) {
		Message curMess = new Message();
		
		String messageValue = messElem.getAttribute("name");
		curMess.parseStr(messageValue);
		
		//�������� Id ����������� Event (fragment)
		String resEvId = messElem.getAttribute("receiveEvent");		
		
		//��������� ��������� �� lifeline
		LifeLine resLifeline = fragmentsWithLifeln.get(resEvId);
		
		if(resLifeline != null)
			curMess.setTarget(resLifeline);	///� ��������� ��������� �� ���������� Event
		else
			logger.warn("recieve Event was not find before message");
				
		//�������� Id ������������� Event
		String sendEvId = messElem.getAttribute("sendEvent");
		LifeLine sendLifeln = fragmentsWithLifeln.get(sendEvId);
		
		if(sendLifeln != null)
			curMess.setSource(sendLifeln);
		else
			logger.warn("send Event was not find before message");			
		
//		String messSort = messElem.getAttribute("messageSort");
//		if(messSort.equals("synchCall"))  
//			curMess.setMessageSort(MessageSort.SYNCH_CALL);
//		
//		if(messSort.equals("reply"))  
//			curMess.setMessageSort(MessageSort.ASYNCH_SIGNAL);
		
		//�������� ������ �� ����� �� ���������
		CoreParserImpl coreParse = new CoreParserImpl();
		Element extens = (Element)messElem.getElementsByTagName("xmi:Extension").item(0);
		String methodStr = coreParse.getAttrByNameAndTag(extens, "modelTransition", "from");
		int index;
		MMethod refMethod = null;
		if(methodStr != null){
			//for (int i = methodStr.length(); i!=0 || methodStr.in[i] != "$"; i--) {
			index = methodStr.indexOf("$");
			if(index != -1){
				String id4Meth = methodStr.substring(index+1, methodStr.length()-1);
				if(id4Meth != null){
					refMethod = ParsersTool.getInstanceClassParser().getMethodsWithId().get(id4Meth);
					if(refMethod != null){
						curMess.setMethodName(refMethod.getName());
						List<Parameter> parameters = refMethod.getParameters();
						int paramCount;
						if(parameters == null)
							paramCount = 0;
						else
							paramCount = parameters.size();
						curMess.setParamCount(paramCount);
					}
				}					
			}
		}	
		
		return curMess;
	}
	public boolean checkedConnections(){
		boolean flag = true;
		Collection<Message> allMes = messagesWithId.values();
		
		for(Message mes : allMes){
			if(mes.getTarget() == null){
				logger.error("not all messages has RecieveEvent");
				flag = false;
			}
			if(mes.getSource() == null){
				logger.error("not all events has SendEvent");
				flag = false;
			}
		}
		
		return flag;
	}
}
