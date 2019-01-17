package search;

import evaluation.Evaluator;
import offspring.OffspringGenerator;
import termination.SearchTerminator;

public interface SearchEngineFactory<OBJ> {

	public void setEvaluator(Evaluator<OBJ> ev);
	public void setSearchTerminator(SearchTerminator<OBJ> terminator);
	public void setOffspringGenerator(OffspringGenerator<OBJ> generator);
	
	public SearchEngine<OBJ> createSeachEngine();
}
