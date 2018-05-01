package geneticEvolution.evolvers;

import java.util.ArrayList;
import java.util.List;

import geneticEvolution.GeneralGeneticEvolution;
import geneticEvolution.GeneralGeneticEvolutionSpec;
import geneticEvolution.optimizablegenerations.BasicOptimizableGeneration;

public class SingleGenerationEvolution<T> extends GeneralGeneticEvolution<T> {

	public SingleGenerationEvolution(GeneralGeneticEvolutionSpec<T> spec) {
		super(spec);
		
		List<T> optimizableList = new ArrayList<T>(spec.getIndividualCount());
		
		for (int i = 0 ; i < spec.getIndividualCount(); i++) {
			
			T temp = getModifier().generate();
			spec.getModifier().randomize(temp, spec.getBias(), spec.getGenerationSpread());
			optimizableList.add(temp);
			
		}
		
		setGeneration(new BasicOptimizableGeneration<T>(optimizableList));
		
	}
	
	@Override
	public void run() {
		super.run();
		getTester().test(getGeneration());
	}

	
}
