package search;

import evaluation.Evaluator;
import offspring.OffspringGenerator;
import termination.SearchTerminator;

public interface SearchOracle<OBJ> {

	public Evaluator<OBJ> getEvaluator();
	public SearchTerminator<OBJ> getSearchTerminator();
	public OffspringGenerator<OBJ> getOffspringGenerator();
	
}
