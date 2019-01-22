package test;

import java.util.ArrayList;
import java.util.List;

import evaluation.Evaluator;
import objectInterface.ObjectToolkit;
import population.BasicPopulation;
import population.Individual;
import population.Population;
import search.Search;
import search.extensions.EvaluatorNotSetException;
import search.extensions.MultiEvaluable;
import search.extensions.MultiObjectToolkit;
import search.extensions.ToolkitNotSetException;
import tasks.SearchTask;


public class RandomSearchImplementation<OBJ> implements Search<OBJ>, MultiObjectToolkit<OBJ>, MultiEvaluable<OBJ> {

	private Population<OBJ> population;
	
	private boolean evaluated;
	
	private ObjectToolkit<OBJ> toolkit;
	private Evaluator<OBJ> eval;
	
	public RandomSearchImplementation(Population<OBJ> population , ObjectToolkit<OBJ> toolkit , Evaluator<OBJ> eval) {
		
		setPopulation(population);
		setEvaluator(eval);
		setObjectToolkit(toolkit);
		
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
		
		
		if (this.eval == null)
			throw new EvaluatorNotSetException();
		
		if (!this.evaluated) {
			
			SearchTask task = eval.generateEvaluation(population);
			task.run();
			
		}
		
		this.evaluated = true;
		
	}

	@Override
	public void generateOffspring() {
		
		if (this.toolkit == null)
			throw new ToolkitNotSetException();
		
		List<OBJ> newGen = new ArrayList<>();
		
		for (@SuppressWarnings("unused") Individual<OBJ> in :population) {
			
			OBJ newInd = toolkit.constructor();
			toolkit.randomize(newInd);
			
			newGen.add(newInd);
			
		}
		
		population = new BasicPopulation<>(newGen);
		this.evaluated = false;
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
