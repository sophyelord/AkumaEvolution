package neuralnetworks;
import java.util.Random;

import exceptions.InputSizeNotConsistentException;

public class NeuralNetwork{
	
	private NeuralNetworkSpec spec;
	private WeightsAndBiases wAndB;
	
	public NeuralNetwork(NeuralNetworkSpec spec){
		
		if (spec == null)
			throw new NullPointerException("Null NeuralNetworkSpec reference");
		
		this.spec = spec;
		this.wAndB = new WeightsAndBiases(spec);
	}

	
	public float[] infer(float[] input) {
		if (input == null)
			throw new NullPointerException("Null float array reference");
		
		if (input.length != spec.getInputLayerNeurons())
			throw new InputSizeNotConsistentException("The size of the input array ("+input.length+") must be equal to the number of neurons in the input layer (" + spec.getInputLayerNeurons() + ")");
		
		float[][] flipArray = new float[2][spec.getHiddenLayerNeurons()];

		flipArray[0] = squishLayer(wAndB.processLayer(squishLayer(input), 0));
		
		for (int i = 1; i < spec.getHiddenLayerCount() ; i++) {
			flipArray[i % 2] = squishLayer(wAndB.processLayer(flipArray[(i + 1) % 2],i));
		}
		
		return squishLayer(wAndB.processLayer(flipArray[(spec.getHiddenLayerCount() + 1) % 2],spec.getHiddenLayerCount()));
		
	}

	public NeuralNetworkSpec getSpec() {
		return spec;
	}
	
	public WeightsAndBiases getWeightsAndBiases() {
		return wAndB;
	}
	
	
	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		str.append("Neural network specs:\n");
		str.append(spec.toString());
		str.append("\n");
		str.append("Neural network weight and biases:\n");
		str.append(wAndB.toString());
		
		return str.toString();
		
	}
	
	
	/*
	 * Static methods
	 */
	public static NeuralNetwork randomize(NeuralNetwork net,float spread) {
		
		if (net == null)
			throw new NullPointerException("Null NeuralNetwork reference");
		
		Random r = new Random();
		
		//Randomize weights
		for (int i = 0 ; i < net.wAndB.getWeightTensorSize() ; i++) {
			net.wAndB.setIthWeight(i, (r.nextFloat() - 0.5f)*2*spread);
		}
		
		//Randomize biases
		for (int i = 0 ; i < net.wAndB.getBiasMatrixSize() ; i++) {
			net.wAndB.setIthBias(i, (r.nextFloat() - 0.5f)*2*spread);
		}
		
		return net;
	}
	
	public static NeuralNetwork gaussianMutate(NeuralNetwork network, float stdev) {
		
		if (network == null)
			throw new NullPointerException("Null NeuralNetwork reference");
		
		NeuralNetwork mutated = new NeuralNetwork(network.spec);
		Random r = new Random();
		
		//Mutate weights
		for (int i = 0 ; i < mutated.wAndB.getWeightTensorSize() ; i++ ) {
			mutated.wAndB.setIthWeight(i, (float)(r.nextGaussian()*stdev + network.wAndB.getIthWeight(i)));
		}
		
		//Mutate biases
		for (int i = 0 ; i < mutated.wAndB.getBiasMatrixSize() ; i++) {
			mutated.wAndB.setIthBias(i, (float)(r.nextGaussian()*stdev + network.wAndB.getIthBias(i)));
		}
		
		return mutated;
		
	}

	public static NeuralNetwork discreteCrossover(NeuralNetwork A, NeuralNetwork B, float dominanceFactor) {
	
		if (A == null || B == null)
			throw new NullPointerException("Null NeuralNetwork reference");
		
		NeuralNetwork son = new NeuralNetwork(A.spec);
		Random r = new Random();
		float p = 1 / (1 + dominanceFactor);
		
		//crossover weights
		for (int i = 0 ; i < son.wAndB.getWeightTensorSize() ; i++) {
			son.wAndB.setIthWeight(i, (r.nextFloat() > p) ? A.wAndB.getIthWeight(i) : B.wAndB.getIthWeight(i));
		}
		
		//crossover biases
		for (int i = 0 ; i < son.wAndB.getBiasMatrixSize() ; i++) {
			son.wAndB.setIthBias(i, (r.nextFloat() > p) ? A.wAndB.getIthBias(i) : B.wAndB.getIthBias(i));
		}
		
		return son;
	}
	
	public static NeuralNetwork percentileCrossover(NeuralNetwork A, NeuralNetwork B, float dominanceFactor) {
	
		if (A == null || B == null)
			throw new NullPointerException("Null NeuralNetwork reference");
		
		NeuralNetwork son = new NeuralNetwork(A.spec);
		float p = 1 / (1 + dominanceFactor);
		
		//crossover weights
		for (int i = 0 ; i < son.wAndB.getWeightTensorSize() ; i++) {
			son.wAndB.setIthWeight(i,  (1-p)*A.wAndB.getIthWeight(i) + p*B.wAndB.getIthWeight(i));
		}
		
		//crossover biases
		for (int i = 0 ; i < son.wAndB.getBiasMatrixSize() ; i++) {
			son.wAndB.setIthBias(i, (1-p)*A.wAndB.getIthBias(i) + p*B.wAndB.getIthBias(i));
		}
		
		return son;
	}
	
	private float[] squishLayer(float[] layerActivations) {
		
		if (layerActivations == null)
			throw new NullPointerException("Null float array reference");
		
		for (int i = 0 ; i < layerActivations.length ; i++) {
			
			layerActivations[i] = spec.getSquisher().squish(layerActivations[i]);
		}
		
		return layerActivations;
		
		
	}
	
}
