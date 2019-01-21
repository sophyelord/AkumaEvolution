package search;

import population.Population;

//Full control of the search
public interface Search<OBJ> {
	
	//Initialization and reseting of the population
	public void setPopulation(Population<OBJ> population); 
	
	//Gets the current population, evaluated or not
	public Population<OBJ> getPopulation();
	
	//Evaluate the population
	public void evaluate();

	//Generate the population offspring, replacing current population with it
	public void generateOffspring();
	
	//Terminate the search
	public void terminateSearch();
	
}
