package geneticEvolution;

import java.util.Iterator;

public interface OptimizableGeneration<T> extends Iterable<T>{
	
	//Extended
	public Iterator<T> iterator();
	
	//Setters
	public void setFitness(T optimizable,float fitness);
	
	//Getters
	public T getIBestOptimizable(int i);
	public float getIBestFitness(int i);
	public int getIndividualCount();
	
}
