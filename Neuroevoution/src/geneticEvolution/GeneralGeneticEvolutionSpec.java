package geneticEvolution;

public class GeneralGeneticEvolutionSpec<T> {

	private OptimizableModifier<T> mod;
	private OptimizableTester<T> test;
	private float generationSpread;
	private T bias;
	private int individualCount;
	
	public GeneralGeneticEvolutionSpec(OptimizableModifier<T> mod, OptimizableTester<T> test,int individualCount, float generationSpread,
			T bias) {
		super();
		
		if (mod == null)
			throw new NullPointerException("Null OptimizableModifier reference");
		
		if (test == null)
			throw new NullPointerException("Null OptimizableTester reference");
		
		if (bias == null)
			throw new NullPointerException("Null Optimizable reference");
		
		this.mod = mod;
		this.test = test;
		this.generationSpread = generationSpread;
		this.bias = bias;
		this.individualCount = individualCount;
	}
	
	public GeneralGeneticEvolutionSpec(OptimizableModifier<T> mod, OptimizableTester<T> test,int individualCount, float generationSpread) {
		super();
		
		if (mod == null)
			throw new NullPointerException("Null OptimizableModifier reference");
		
		if (test == null)
			throw new NullPointerException("Null OptimizableTester reference");
		
		
		
		this.mod = mod;
		this.test = test;
		this.generationSpread = generationSpread;
		this.bias = mod.generate();
		this.individualCount = individualCount;
	}
	
	public OptimizableModifier<T> getModifier() {
		return mod;
	}

	public OptimizableTester<T> getTester() {
		return test;
	}

	public float getGenerationSpread() {
		return generationSpread;
	}

	public T getBias() {
		return bias;
	}

	public int getIndividualCount() {
		return individualCount;
	}
	
}
