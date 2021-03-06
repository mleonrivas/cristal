package raci2bpmn;

import java.util.List;

import javax.xml.bind.JAXBElement;

import bpmn.BPMNDiagram;
import bpmn.BPMNPlane;
import bpmn.BPMNShape;
import bpmn.DiagramElement;
import bpmn.ObjectFactory;
import bpmn.TDefinitions;
import bpmn.TFlowNode;
import bpmn.TStartEvent;

public class DiagramUpdater {

	public interface ShapeUpdater {
		public void update(BPMNShape shape);
	}
	
	
	List<BPMNDiagram> diagrams;
	
	
	public DiagramUpdater(TDefinitions definitions) {
		diagrams = definitions.getBPMNDiagram();
	}
	
	public void updateShape(TFlowNode bpmnFlowNode, ShapeUpdater updater) {
		for (BPMNDiagram diagram : diagrams) {
			updatePlane(diagram.getBPMNPlane(), bpmnFlowNode, updater);
		}
		
	}
	
	public void addShape(BPMNShape newElem) {
		for (BPMNDiagram diagram : diagrams) {
			addShape(diagram.getBPMNPlane(), newElem);
		}
		
	}

	private void updatePlane(BPMNPlane plane, TFlowNode flowNode, ShapeUpdater updater) {
		List<JAXBElement<? extends DiagramElement>> diagramElements = plane
				.getDiagramElement();
		for (JAXBElement<? extends DiagramElement> elem : diagramElements) {
			DiagramElement de = elem.getValue();
			if (de instanceof BPMNPlane) {
				updatePlane((BPMNPlane) de, flowNode, updater);
			} else if (de instanceof BPMNShape) {
				updateShape((BPMNShape) de, flowNode, updater);
			}
		}
	}

	private void updateShape(BPMNShape shape, TFlowNode subprocess, ShapeUpdater updater) {
		if (shape.getBpmnElement().getLocalPart().equals(subprocess.getId())) {
			updater.update(shape);
		}

	}
	
	private void addShape(BPMNPlane plane, BPMNShape newElem) {
		List<JAXBElement<? extends DiagramElement>> diagramElements = plane.getDiagramElement();
		diagramElements.add(new ObjectFactory().createBPMNShape(newElem));
	}

}
