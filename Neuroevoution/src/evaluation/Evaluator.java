package evaluation;

import population.Population;

public interface Evaluator<OBJ> extends Runnable {

	public void setPopulation(Population<OBJ> population); //Lazy computing, does not do much but defining the computation
	
	

}
