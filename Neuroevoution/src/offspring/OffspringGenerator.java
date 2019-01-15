package offspring;

public interface OffspringGenerator<OBJ> {

	//Initialize runnable
	public void init();
	
	//While running
	public void generateOffspring();
	
	public void getPopulation();
	public void setPopulation(); //To set evaluated population
	
}
