package offspring;

import genealogy.GenealogyObserver;
import population.EvaluatedPopulation;
import population.Population;

public interface OffspringGenerator<OBJ> extends GenealogyObserver{

	//Initialize
	public void init();
	
	//While running
	public void generateOffspring();
	
	public Population<OBJ> getPopulation();
	public void setPopulation(EvaluatedPopulation<OBJ> population); //To set evaluated population
	
}
