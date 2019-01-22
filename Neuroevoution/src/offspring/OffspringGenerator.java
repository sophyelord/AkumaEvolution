package offspring;

import population.Population;
import search.OracleObserver;

public interface OffspringGenerator<OBJ> {

	//Initialize
	public void init();
	
	//While running
	public void generateOffspring();
	
	public Population<OBJ> getPopulation();
	public void setGeneration(Generation<OBJ> population); //To set evaluated population
	
}
