package evaluation;

import genealogy.GenealogyObserver;
import population.EvaluatedPopulation;
import population.Population;

public interface Evaluator<OBJ> extends Runnable, GenealogyObserver{

	public void setPopulation(Population<OBJ> population); //Lazy computing, does not do much but defining the computation
	
	public EvaluatedPopulation<OBJ> getPopulation(); //Evaluated population

}
