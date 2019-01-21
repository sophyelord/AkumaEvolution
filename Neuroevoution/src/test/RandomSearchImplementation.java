package test;

import java.util.ArrayList;
import java.util.List;

import evaluation.Evaluator;
import objectInterface.ObjectToolkit;
import population.BasicPopulation;
import population.Individual;
import population.Population;
import search.Search;
import search.extensions.MultiEvaluable;
import search.extensions.MultiObjectToolkit;


public class RandomSearchImplementation<OBJ> implements Search<OBJ>, MultiObjectToolkit<OBJ>, MultiEvaluable<OBJ> {

	private Population<OBJ> population;
	
	private boolean evaluated;
	private boolean terminated;
	
	
	private ObjectToolkit<OBJ> toolkit;
	private Evaluator<OBJ> eval;
	
	public RandomSearchImplementation() {
		this.evaluated = false;
		this.terminated = false;
	}
	
	@Override
	public void setPopulation(Population<OBJ> population) {
		this.population = population;
		this.evaluated = false;
	}

	@Override
	public Population<OBJ> getPopulation() {
		
		return this.population;
	}

	@Override
	public void evaluate() {
		
		if (!this.evaluated) {
			
			eval.setPopulation(population);
			eval.run();
			
		}
		
		this.evaluated = true;
		
	}

	@Override
	public void generateOffspring() {
		
		List<OBJ> newGen = new ArrayList<>();
		
		for (Individual<OBJ> in :population) {
			
			OBJ newInd = toolkit.constructor();
			toolkit.randomize(newInd);
			
			newGen.add(newInd);
			
		}
		
		population = new BasicPopulation<>(newGen);
		
	}

	@Override
	public void terminateSearch() {
		
		this.terminated = true;
		
	}

	@Override
	public void setEvaluator(Evaluator<OBJ> eval) {
		
		this.eval = eval;
		
	}

	@Override
	public void setObjectToolkit(ObjectToolkit<OBJ> toolkit) {
		
		this.toolkit = toolkit;
		
	}

}
