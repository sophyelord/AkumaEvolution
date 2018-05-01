package neuralnetworks;

import exceptions.InputSizeNotConsistentException;

public class WeightsAndBiases {

	private NeuralNetworkSkelleton skelleton;
	private float[] weightAndBias;
	
	public WeightsAndBiases(NeuralNetworkSpec spec) {
		
		if (spec == null)
			throw new NullPointerException("Null NeuralNetworkSpec reference");
		
		skelleton = new NeuralNetworkSkelleton(spec);
		weightAndBias = new float[skelleton.getBiasMatrixSize() + skelleton.getWeightTensorSize()];
		
	}
	
	
	public float[] processLayer(float[] in, int layer) {
		
		if (in.length != skelleton.getLayerSize(layer-1))
			throw new InputSizeNotConsistentException("The size of the input array ("+in.length+") must be equal to the size of the layer to be processed (" +skelleton.getLayerSize(layer-1) + ")");
		
		if (layer < 0 || layer > skelleton.getLayerCount())
			throw new IndexOutOfBoundsException("Invalid process layer index " + layer + " ,valid values range from 0 to the number of hidden layers in the neural network (" + skelleton.getLayerCount() + ")");
		
		float[] result = new float[skelleton.getLayerSize(layer)];
		
		
		for (int i = 0 ; i < skelleton.getLayerSize(layer) ; i++) {
			for (int j = 0 ; j < skelleton.getLayerSize(layer-1) ; j++) {
				
				result[i] += in[j]*weightAndBias[skelleton.getWeightIndex(layer,i,j)]; 
				
			}
			
			result[i] += weightAndBias[skelleton.getBiasIndex(layer, i) + skelleton.getWeightTensorSize()];
			
			
		}
		
		return result;
	}
	
	public float getIthWeight(int i) {
		if (i < 0 || i >= skelleton.getWeightTensorSize())
			throw new IndexOutOfBoundsException("Invalid weight index " + i + " ,valid values range from 0 to the size of the weight tensor of the neural network minus one (" + skelleton.getWeightTensorSize() + ")");
		return weightAndBias[i];
	}
	
	public float getIthBias(int i) {
		
		if (i < 0 || i >= skelleton.getBiasMatrixSize())
			throw new IndexOutOfBoundsException("Invalid bias index " + i + " ,valid values range from 0 to the size of the bias matrix of the neural network minus one (" + skelleton.getBiasMatrixSize() + ")");

		return weightAndBias[i+skelleton.getWeightTensorSize()];
	}
	
	public void setIthWeight(int i , float weight) {
		if (i < 0 || i >= skelleton.getWeightTensorSize())
			throw new IndexOutOfBoundsException("Invalid weight index " + i + " ,valid values range from 0 to the size of the weight tensor of the neural network minus one (" + skelleton.getWeightTensorSize() + ")");

		weightAndBias[i] = weight;
	}
	
	public void setIthBias(int i, float bias) {
		if (i < 0 || i >= skelleton.getBiasMatrixSize())
			throw new IndexOutOfBoundsException("Invalid bias index " + i + " ,valid values range from 0 to the size of the bias matrix of the neural network minus one (" + skelleton.getBiasMatrixSize() + ")");

		
		weightAndBias[i+skelleton.getWeightTensorSize()] = bias;
	}
	
	public int getBiasMatrixSize() {
		return skelleton.getBiasMatrixSize();
	}
	
	public int getWeightTensorSize() {
		return skelleton.getWeightTensorSize();
	}

	public String toString() {
		
		StringBuilder str = new StringBuilder();
	
		//Weights
		str.append("Weights: \n");
		for (int i = 0 ; i < skelleton.getLayerCount() ; i++) {
			str.append("Layer " + i + ":\n");
			
			for (int j = 0 ;  j < skelleton.getLayerSize(i) ; j++) {
				str.append("Neuron " + j + " weights:\n");

				for (int k = 0 ; k < skelleton.getLayerSize(i-1) ; k++) {
					str.append(k + "\t");
				}
				str.append("\n");
				for (int k = 0 ; k < skelleton.getLayerSize(i-1) ; k++) {
					str.append(String.format("%.1f \t",weightAndBias[skelleton.getWeightIndex(i, j, k)]));
					
				}
				str.append("\n");
				
			}
			
		}
		str.append("\n");
		
		
		//Biases
		str.append("Biases: \n");
		for (int i = 0; i < skelleton.getLayerCount() ; i++) {
			str.append("Layer " + i + ":\n");
			
			for (int j = 0 ; j < skelleton.getLayerSize(i); j++) {
				str.append(j + "\t");
			}
			str.append("\n");
			for (int j = 0 ; j < skelleton.getLayerSize(i); j++) {
				str.append(String.format("%.2f \t",weightAndBias[skelleton.getBiasIndex(i, j) + skelleton.getWeightTensorSize()]));
			}
			str.append("\n");
		}
		
	
		return str.toString();
		
		
	}
}
