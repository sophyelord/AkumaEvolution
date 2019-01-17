package offspring;

import population.EvaluatedPopulation;
import population.Population;
import search.OracleObserver;

public interface OffspringGenerator<OBJ> extends OracleObserver<OBJ>{

	//Initialize
	public void init();
	
	//While running
	public void generateOffspring();
	
	public Population<OBJ> getPopulation();
	public void setPopulation(EvaluatedPopulation<OBJ> population); //To set evaluated population
	
}
