package geneticEvolution;

import exceptions.NotEvolvedGenerationException;
import exceptions.NullStartingGenerationException;

public abstract class GeneralGeneticEvolution<T> implements GeneticEvolution<T>{

	private OptimizableGeneration<T> gen;
	private GeneralGeneticEvolutionSpec<T> spec;
	private boolean evolved;
	
	public GeneralGeneticEvolution(GeneralGeneticEvolutionSpec<T> spec) {
		
		if (spec == null)
			throw new NullPointerException("Null GeneralGeneticEvolutionSpec reference");
		
		this.spec = spec;
		this.evolved = false;
	}
	
	public void run() {
		
		if (gen == null)
			throw new NullStartingGenerationException("Starting generation has not been set");
		evolved = true;
	}
	
	protected final void setGeneration(OptimizableGeneration<T> gen) {
		this.gen = gen;
		evolved = false;
	}
	
	public final OptimizableGeneration<T> getGeneration(){
		
		if (evolved)
			return gen;
		else
			throw new NotEvolvedGenerationException("Genetic evolution has not been applied yet.");
	}
	
	public final OptimizableModifier<T> getModifier() {
		return spec.getModifier();
	}
	
	public final OptimizableTester<T> getTester() {
		return spec.getTester();
	}

	public final boolean isEvolved() {
		return evolved;
	}	
	
}
