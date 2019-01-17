package search;

import evaluation.Evaluator;
import offspring.OffspringGenerator;
import population.EvaluatedPopulation;
import population.Population;
import terminationCriteria.SearchTerminator;

public class SearchEngine<OBJ> implements Runnable{

	private OffspringGenerator<OBJ> generator;
	private Evaluator<OBJ> evaluator;
	private SearchTerminator<OBJ> terminator;
	
	private EvaluatedPopulation<OBJ> evolvedSolution;
	
	public SearchEngine(OffspringGenerator<OBJ> generator, Evaluator<OBJ> evaluator, SearchTerminator<OBJ> terminator){
		
		this.generator = generator;
		this.evaluator = evaluator;
		this.terminator = terminator;
	}


	@Override
	public void run() {
		
		boolean terminate = false;
		
		generator.init();
		
		while (!terminate) {
		
			generator.generateOffspring();
			Population<OBJ> pop = generator.getPopulation();
			
			evaluator.setPopulation(pop);
			evaluator.run();
			evolvedSolution = evaluator.getPopulation();
			
			terminator.evaluateCriterion(evolvedSolution);
			terminate = terminator.searchOver();
		}	
		
	}
	
	public EvaluatedPopulation<OBJ> getEvolvedSolution(){
		return evolvedSolution;
	}
}
