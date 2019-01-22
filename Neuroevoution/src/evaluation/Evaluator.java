package evaluation;

import population.Population;
import tasks.SearchTask;

public interface Evaluator<OBJ>{

	public SearchTask generateEvaluation(Population<OBJ> population); //Lazy computing, does not do much but defining the computation


}
