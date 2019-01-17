package terminationCriteria;

import genealogy.GenealogyObserver;
import population.EvaluatedPopulation;

public interface SearchTerminator<OBJ> extends GenealogyObserver{

	public void evaluateCriterion(EvaluatedPopulation<OBJ> population);
	
	public boolean searchOver();
}
