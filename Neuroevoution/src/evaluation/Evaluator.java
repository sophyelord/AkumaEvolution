package evaluation;

public interface Evaluator<OBJ> extends Runnable {

	public void setPopulation(); //Lazy computing, does not do much but defining the computation
	
	public void getPopulation(); //Evaluated population
	
}
