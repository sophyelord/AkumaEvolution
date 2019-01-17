package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import evaluation.Evaluator;
import objectInterface.ObjectToolkit;
import offspring.OffspringAlgorithm;
import offspring.OffspringGenerator;
import offspring.ToolkitInsuficientException;
import population.EvaluatedIndividual;
import population.EvaluatedPopulation;
import population.Population;
import search.SearchEngine;
import terminationCriteria.SearchTerminator;

public class SimpleTest1 {

	public static void main(String[] args) {
		
		OffspringAlgorithm<float[]> algo = new OffspringAlgorithm<float[]>() {
			
			
			@Override
			public OffspringGenerator<float[]> bindToolkit(ObjectToolkit<float[]> toolkit)
					throws ToolkitInsuficientException {
				
				return new OffspringGenerator<float[]>() {

					Population<float[]> pop;
					EvaluatedPopulation<float[]> evPop;
					
					@Override
					public void init() {
					
						
						pop = new Population<float[]>() {
							
							List<float[]> pop = randomize();
							
							private List<float[]> randomize() {
								
								List<float[]> l = new ArrayList<>();
								for (int j = 0 ; j < 100 ; j++) {
									float[] f = new float[100];
									
									Random r = new Random();
									
									for (int i = 0 ; i < f.length ; i++) {
										f[i] = (r.nextFloat()-0.5f)*20;
									}
									l.add(f);
								}
								return l;
								
							}
							
							@Override
							public Iterator<float[]> iterator() {
								return pop.iterator();
							}

							@Override
							public int getSize() {
								return pop.size();
							}
						};
						
					}

					@Override
					public void generateOffspring() {
						if (evPop != null) {
							init();
							evPop = null;
						}
						
					}

					@Override
					public Population<float[]> getPopulation() {
						
						return pop;
					}

					@Override
					public void setPopulation(EvaluatedPopulation<float[]> population) {
						evPop = population;
						
					}
				};
			}

			@Override
			public Class<?>[] getToolkitImplementationRequirements() {
				
				return new Class[0];
			}
		};
		
		OffspringGenerator<float[]> og = null;
		try {
			og = algo.bindToolkit(null);
		} catch (ToolkitInsuficientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Evaluator<float[]> ev = new Evaluator<float[]>() {

			Population<float[]> population;
			EvaluatedPopulation<float[]> evPop;
			
			@Override
			public void run() {
				evPop = new EvaluatedPopulation<float[]>() {
					
					@Override
					public Iterator<EvaluatedIndividual<float[]>> iterator() {
						
						Iterator<float[]> it  = population.iterator();
						
						return new Iterator<EvaluatedIndividual<float[]>>() {

							@Override
							public boolean hasNext() {
								
								return it.hasNext();
							}

							@Override
							public EvaluatedIndividual<float[]> next() {
								float[] fa = it.next();
								float sc = 0;
								for (float f: fa ) {
									
									sc += f;
								}
								
								return new EvaluatedIndividual<float[]>(fa, sc);
							}
						};
					}
					
					@Override
					public int getSize() {
						// TODO Auto-generated method stub
						return population.getSize();
					}
				};
				
			}

			@Override
			public void setPopulation(Population<float[]> population) {
				this.population = population;
				
			}

			@Override
			public EvaluatedPopulation<float[]> getPopulation() {
				return evPop;
				
			}
		};
		
		SearchTerminator<float[]> st = new SearchTerminator<float[]>() {

			boolean searchO = false;
			
			@Override
			public void evaluateCriterion(EvaluatedPopulation<float[]> population) {
				
				float mean = 0;
				
				for (EvaluatedIndividual<float[]> ind: population) {
					mean += ind.getScore();
				}
				
				mean /= population.getSize();
				
				System.out.println(mean);
				float var = 0;
				
				for (EvaluatedIndividual<float[]> ind: population) {
					var += (ind.getScore() - mean)*(ind.getScore() - mean);
				}
				
				if (var < 100) {
					searchO = true;
				}
			}

			@Override
			public boolean searchOver() {
				// TODO Auto-generated method stub
				return searchO;
			}
		};
		
		SearchEngine<float[]> engine = new SearchEngine<>(og, ev, st);
		engine.run();
	}
}
