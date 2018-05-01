package geneticEvolution;

public interface OptimizableModifier<T> {

	//Generates an Optimizable in the origin of the space
	public T generate();

	//Randomizes an Optimizable with the center on "bias" and with spread "stdev"
	public T randomize(T t, T bias ,float stdev);

}
