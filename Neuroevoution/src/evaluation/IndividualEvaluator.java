package evaluation;

import population.Individual;
import tasks.SearchTask;

public interface IndividualEvaluator<OBJ> {

	public SearchTask generateIndividualEvaluation(Individual<OBJ> obj);
	
	
}
