package geneticEvolution.evolvers;

import java.util.ArrayList;
import java.util.List;

import geneticEvolution.GeneralGeneticEvolution;
import geneticEvolution.GeneralGeneticEvolutionSpec;
import geneticEvolution.OptimizableGeneration;
import geneticEvolution.optimizablegenerations.BasicOptimizableGeneration;

public class BestMutationEvolution<T> extends GeneralGeneticEvolution<T>{

	private int iterations;
	private float spreadPerIteration;
	
	public BestMutationEvolution(GeneralGeneticEvolutionSpec<T> spec,int iterations,float spreadPerIteration) {
		super(spec);
		this.iterations = iterations;
		this.spreadPerIteration = spreadPerIteration;
		List<T> optimizableList = new ArrayList<T>();
		
		int numInd = spec.getIndividualCount();
		for (int i = 0 ; i < numInd ; i++) {
			T temp = getModifier().generate();
			temp = spec.getModifier().randomize(temp, spec.getBias(), spec.getGenerationSpread());
			optimizableList.add(temp);
		}
		
		OptimizableGeneration<T> gen = new BasicOptimizableGeneration<>(optimizableList);
		setGeneration(gen);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println(isEvolved());
		int numInd = getGeneration().getIndividualCount();
		for (int i = 0 ; i < iterations ; i++) {
			getTester().test(getGeneration());
			if (i + 1 != iterations) {
				T t = getGeneration().getIBestOptimizable(0);
				List<T> optimizableList = new ArrayList<T>();
				for (int j = 0 ; j < numInd ; j++) {
					T temp = getModifier().generate();
					temp = getModifier().randomize(temp,t,spreadPerIteration);
					optimizableList.add(temp);
				}
				OptimizableGeneration<T> gen = new BasicOptimizableGeneration<T>(optimizableList);
				setGeneration(gen);
				super.run();
			}
		}
	}

	
}
