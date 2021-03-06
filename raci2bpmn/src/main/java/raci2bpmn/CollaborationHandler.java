package raci2bpmn;

import java.util.List;

import javax.xml.bind.JAXBElement;

import bpmn.ObjectFactory;
import bpmn.TCollaboration;
import bpmn.TDefinitions;
import bpmn.TMessageFlow;
import bpmn.TParticipant;
import bpmn.TRootElement;

public class CollaborationHandler {

	private TCollaboration collaboration;
	private ObjectFactory objectFactory;

	public CollaborationHandler(TDefinitions definitions) {
		collaboration = null;
		objectFactory = new ObjectFactory();
		
		List<JAXBElement<? extends TRootElement>> rootElements = definitions.getRootElement();
		
		for (JAXBElement<? extends TRootElement> elem: rootElements) {
			if (elem.getDeclaredType() == bpmn.TCollaboration.class)
				collaboration = (TCollaboration) elem.getValue();
		}
		
		if (collaboration == null) {
			collaboration = new TCollaboration();
			collaboration.setId(IdGenerator.createId());
			definitions.getRootElement().add(objectFactory.createCollaboration(collaboration));
		}
	}

	public void addParticipants(List<TParticipant> participants) {
		for (TParticipant p : participants) {
			collaboration.getParticipant().add(p);
		}
		
	}

	public void addMessageFlows(List<TMessageFlow> messageFlows) {
		for (TMessageFlow m: messageFlows) {
			collaboration.getMessageFlow().add(m);
		}
	}
	
	

}
