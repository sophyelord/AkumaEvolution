package test;

import java.util.Arrays;
import java.util.Random;

import evaluation.Evaluator;
import evaluation.IndividualEvaluator;
import evaluation.IndividualEvaluatorImp;
import objectInterface.ObjectToolkit;
import objectInterface.samples.FloatVectorToolkit;
import population.BasicPopulation;
import population.Individual;
import population.Population;
import population.PopulationAnalyzer;
import population.extensions.EditableIndividual;
import tasks.SearchTask;

class SimpleTest1 {

	public static void main(String[] args) {
	
		
		
		IndividualEvaluator<Float[]> indEval = new IndividualEvaluator<Float[]>() {

			@Override
			public SearchTask generateIndividualEvaluation(Individual<Float[]> obj) {
				
				return new SearchTask() {
				
					private EditableIndividual<Float[]> ind = (EditableIndividual<Float[]>)obj;
					
					@Override
					public void run() {
						
						Float[] flo = ind.getIndividual();
						
						float sum = 0;
						
						for (Float f : flo) {
							sum += f;
						}
						
						ind.setScore(sum);
						
					}
				};
			}
		};
		
		Evaluator<Float[]> eval = new IndividualEvaluatorImp<>(indEval);
		
		ObjectToolkit<Float[]> fvt = new FloatVectorToolkit(4, 0, 2);
		
		Float[][] startingSet = new Float[100][4];
		
		Random r = new Random();
		
		for (int i = 0 ; i < startingSet.length ; i++) {
			for (int j = 0 ; j < startingSet[i].length ; j++)
				startingSet[i][j] = r.nextFloat()*2;
		}
		
		
		Population<Float[]> population = new BasicPopulation<Float[]>(Arrays.asList(startingSet));
		
		RandomSearchImplementation<Float[]> rsi = new RandomSearchImplementation<Float[]>(population, fvt, eval);
		rsi.evaluate();
		
		
		float max = Float.NEGATIVE_INFINITY;
		while (true) {
			
			rsi.generateOffspring();
			rsi.evaluate();
			
			population = rsi.getPopulation();
			
			
			@SuppressWarnings("unchecked")
			PopulationAnalyzer<Float[]> pa = (PopulationAnalyzer<Float[]>) population;
			
			if (max < pa.getMaxScoreIndividual().getScore()) {
				max = pa.getMaxScoreIndividual().getScore();
				System.out.println("max_score:" + max);
			}
			
		}
		
		
	}
}
