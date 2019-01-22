package evaluation;

import population.Individual;
import population.Population;
import tasks.SearchTask;

public class IndividualEvaluationTask<OBJ> implements SearchTask {

	
	private IndividualEvaluator<OBJ> eval;
	private Population<OBJ> population;
	
	public IndividualEvaluationTask(IndividualEvaluator<OBJ> eval, Population<OBJ> population) {
		
		this.eval = eval;
		this.population = population;
	}

	@Override
	public void run() {
		
		for (Individual<OBJ> obj : population) {
			
			SearchTask st = eval.generateIndividualEvaluation(obj);
			st.run();
			
		}
		
		
	}

}
