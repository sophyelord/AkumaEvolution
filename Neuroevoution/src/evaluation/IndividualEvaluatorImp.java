package evaluation;

import population.Population;
import tasks.SearchTask;

public class IndividualEvaluatorImp<OBJ> implements Evaluator<OBJ>{

	private IndividualEvaluator<OBJ> evaluator;
	
	public IndividualEvaluatorImp(IndividualEvaluator<OBJ> evaluator) {
		this.evaluator = evaluator;
	}
	
	
	@Override
	public SearchTask generateEvaluation(Population<OBJ> population) {
		
		return new IndividualEvaluationTask<>(evaluator, population);
	}

}
