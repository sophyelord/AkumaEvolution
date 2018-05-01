package test;

import java.util.Random;

import activationsquisher.LogisticSquisher;
import geneticEvolution.GeneralGeneticEvolutionSpec;
import geneticEvolution.GeneticEvolution;
import geneticEvolution.OptimizableGeneration;
import geneticEvolution.OptimizableModifier;
import geneticEvolution.OptimizableTester;
import geneticEvolution.evolvers.SingleGenerationEvolution;
import geneticEvolution.optimizabletester.OutputSumZero;
import metric.EuclideanMetric;
import metric.MetricFunction;
import neuralnetworks.NeuralNetwork;
import neuralnetworks.NeuralNetworkSpec;

public class GenEvolveTest1 {

	public static void main(String[] args) {
		
		
		OptimizableTester<NeuralNetwork> opttest = new OutputSumZero(10, 5);
		OptimizableModifier<NeuralNetwork> optmod = new OptimizableModifier<NeuralNetwork>() {

			@Override
			public NeuralNetwork generate() {
				
				return new NeuralNetwork(new NeuralNetworkSpec(3, 16, 64, 8, new LogisticSquisher()));
			}

			@Override
			public NeuralNetwork randomize(NeuralNetwork t, NeuralNetwork bias, float stdev) {
				 
				return NeuralNetwork.randomize(t, stdev);
			}
		};
		
		GeneralGeneticEvolutionSpec<NeuralNetwork> gges = new GeneralGeneticEvolutionSpec<NeuralNetwork>(optmod, opttest, 100000, 5);
		GeneticEvolution<NeuralNetwork> genevol = new SingleGenerationEvolution<NeuralNetwork>(gges);
		
		genevol.run();
		
		System.out.println(genevol.getGeneration().getIBestOptimizable(0));
		System.out.println(genevol.getGeneration().getIBestFitness(0));
		//System.out.println(realFitness(genevol.getGeneration().getIBestOptimizable(0)));
		
	}

	private static float realFitness(NeuralNetwork n) {
		
			
			Random r = new Random();
			float partial = 0;
			for (int i = 0 ; i < 10000 ; i++) {
				
				float[] fa = new float[n.getSpec().getInputLayerNeurons()];
				
				for (int j = 0 ; j < n.getSpec().getInputLayerNeurons() ;  j++) {
					fa[j] = (r.nextFloat()-0.5f)*10;
				}
				
				float[] temp = n.infer(fa);
				partial += n.getSpec().getOutputLayerNeurons() - sum(temp);
				
				for (float f : temp) {
					System.out.println(f);
				}
				
			}
			
			return partial / 10000;
			
		
	}

	private static float sum(float[] temp) {
		float sum = 0;
		for (float f : temp) {
			sum += f;
		}
		
		return sum;
	}
}
