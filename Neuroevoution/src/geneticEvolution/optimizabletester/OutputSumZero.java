package geneticEvolution.optimizabletester;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import geneticEvolution.OptimizableGeneration;
import geneticEvolution.OptimizableTester;
import metric.EuclideanMetric;
import metric.MetricFunction;
import neuralnetworks.NeuralNetwork;

public class OutputSumZero implements OptimizableTester<NeuralNetwork> {

	private int sampleSize;
	private float sampleSpread;
	
	public OutputSumZero(int sampleSize, float sampleSpread) {
		this.sampleSize = sampleSize;
		this.sampleSpread = sampleSpread;
	}
	
	@Override
	public void test(OptimizableGeneration<NeuralNetwork> generation) {
		
		for (NeuralNetwork n : generation) {
			
			generation.setFitness(n, calculateNetworkFitness(n));
			
		}
		
	}

	private float calculateNetworkFitness(NeuralNetwork n) {
		
		Random r = new Random();
		float partial = 0;
		for (int i = 0 ; i < sampleSize ; i++) {
			
			float[] fa = new float[n.getSpec().getInputLayerNeurons()];
			
			for (int j = 0 ; j < n.getSpec().getInputLayerNeurons() ;  j++) {
				fa[j] = (r.nextFloat()-0.5f)*sampleSpread*2;
			}
			float[] temp = n.infer(fa);
			
			partial += n.getSpec().getOutputLayerNeurons() - sum(temp);
			
		}
		
		return partial / sampleSize;
		
	}

	private float sum(float[] temp) {
		
		float sum = 0;
		for (float f : temp) {
			sum += f;
		}
		
		return sum;
	}

}
