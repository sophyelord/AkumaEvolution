package termination;

import population.Generation;
import search.OracleObserver;

public interface SearchTerminator<OBJ> extends OracleObserver<OBJ>{

	public void evaluateCriterion(Generation<OBJ> population);
	
	public boolean searchOver();
}
