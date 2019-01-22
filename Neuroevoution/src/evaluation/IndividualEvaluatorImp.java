package evaluation;

import population.Individual;
import population.Population;

public class IndividualEvaluatorImp<OBJ> implements Evaluator<OBJ>{

	private Population<OBJ> currentPopulation;
	
	private IndividualEvaluator<OBJ> evaluator;
	
	public IndividualEvaluatorImp(IndividualEvaluator<OBJ> evaluator) {
		this.evaluator = evaluator;
	}
	
	public void setIndividualEvaluator(IndividualEvaluator<OBJ> evaluator) {
		this.evaluator = evaluator;
	}
	
	@Override
	public void run() {
		if (currentPopulation != null) {
			for (Individual<OBJ> i : currentPopulation) {
				evaluator.setIndividual(i);
				evaluator.run();
			}
		}
		
		
	}

	@Override
	public void setPopulation(Population<OBJ> population) {

		this.currentPopulation = population;
		
	}

}
