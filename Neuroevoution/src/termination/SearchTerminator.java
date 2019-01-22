package termination;

import population.Population;

public interface SearchTerminator<OBJ> {

	public void evaluateCriterion(Population<OBJ> population);
	
	public boolean searchOver();
}
