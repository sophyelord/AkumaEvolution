package evaluation;

import population.Individual;

public interface IndividualEvaluator<OBJ> extends Runnable{

	public void setIndividual(Individual<OBJ> obj);
	
	
}
