package geneticEvolution;

public interface GeneticEvolution<T> extends Runnable {

	public void run();
	
	//Getters
	public OptimizableGeneration<T> getGeneration();
	public boolean isEvolved();
	public OptimizableModifier<T> getModifier();
	public OptimizableTester<T> getTester();
}
