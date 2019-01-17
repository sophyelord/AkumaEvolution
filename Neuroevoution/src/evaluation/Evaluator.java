package evaluation;

import population.Generation;
import population.Population;
import search.OracleObserver;

public interface Evaluator<OBJ> extends Runnable, OracleObserver<OBJ> {

	public void setPopulation(Population<OBJ> population); //Lazy computing, does not do much but defining the computation
	
	public Generation<OBJ> getGeneration(); //Evaluated population

}
