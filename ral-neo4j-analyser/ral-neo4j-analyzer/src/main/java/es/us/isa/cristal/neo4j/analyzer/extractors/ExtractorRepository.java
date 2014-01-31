package es.us.isa.cristal.neo4j.analyzer.extractors;

import java.util.Map;

import es.us.isa.cristal.model.TaskDuty;
import es.us.isa.cristal.neo4j.analyzer.exceptions.TaskDutyNotSupportedException;

public class ExtractorRepository {

	private static Map<TaskDuty, ExpressionExtractor> extractors; 
	
	static{
		extractors.put(TaskDuty.RESPONSIBLE, new ResponsibleExprExtractor());
		
	}
	
	
	public static ExpressionExtractor getExtractor(TaskDuty duty){
		if(!extractors.containsKey(duty)){
			throw new TaskDutyNotSupportedException(duty);
		}
		return extractors.get(duty);
		
	}
	
	
}
