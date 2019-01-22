package evaluation;

import Tasks.Task;
import population.Population;

public interface Evaluator<OBJ>{

	public Task generateEvaluation(Population<OBJ> population); //Lazy computing, does not do much but defining the computation


}
