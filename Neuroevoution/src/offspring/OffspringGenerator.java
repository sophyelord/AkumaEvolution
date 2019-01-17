package offspring;

import population.Generation;
import population.Population;
import search.OracleObserver;

public interface OffspringGenerator<OBJ> extends OracleObserver<OBJ>{

	//Initialize
	public void init();
	
	//While running
	public void generateOffspring();
	
	public Population<OBJ> getPopulation();
	public void setGeneration(Generation<OBJ> population); //To set evaluated population
	
}
