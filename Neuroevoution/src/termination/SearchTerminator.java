package termination;

import population.EvaluatedPopulation;
import search.OracleObserver;

public interface SearchTerminator<OBJ> extends OracleObserver<OBJ>{

	public void evaluateCriterion(EvaluatedPopulation<OBJ> population);
	
	public boolean searchOver();
}
