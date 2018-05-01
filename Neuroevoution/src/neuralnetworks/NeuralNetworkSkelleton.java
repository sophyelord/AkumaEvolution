package neuralnetworks;

public class NeuralNetworkSkelleton {

	private NeuralNetworkSpec spec;
	
	private int weightTensorSize;
	private int biasMatrixSize;
	private int hiddenValuesPos;
	
	public NeuralNetworkSkelleton(NeuralNetworkSpec spec) {
		
		if (spec == null)
			throw new NullPointerException("Null NeuralNetworkSpec reference");
		
		this.spec = spec;
		weightTensorSize = spec.getInputLayerNeurons()*spec.getHiddenLayerNeurons() + (spec.getHiddenLayerCount()-1)*spec.getHiddenLayerNeurons()*spec.getHiddenLayerNeurons() + spec.getOutputLayerNeurons()*spec.getHiddenLayerNeurons();
		biasMatrixSize = spec.getHiddenLayerCount()*spec.getHiddenLayerNeurons() + spec.getOutputLayerNeurons();
		hiddenValuesPos = spec.getInputLayerNeurons()*spec.getHiddenLayerNeurons();
		
	}
	
	
	public int getWeightTensorSize() {
		
		return this.weightTensorSize;
	}
	
	public int getBiasMatrixSize() {
		
		return this.biasMatrixSize;
	}

	public int getLayerSize(int layer) {
		
		if (layer < -1 || layer > spec.getHiddenLayerCount())
			throw new IndexOutOfBoundsException("Invalid layer index: " + layer + ", allowed layer indexes range from -1 to the number of hidden layers in the neural network (" + spec.getHiddenLayerCount() + ")");
		
		if (layer == -1) {
			return spec.getInputLayerNeurons();
		}
		else if (layer == spec.getHiddenLayerCount()) {
			return spec.getOutputLayerNeurons();
		}
		else {
			return spec.getHiddenLayerNeurons();
		}
	}

	public int getWeightIndex(int layer, int neuronIndex, int weightIndex) {
		
		if (layer < 0 || layer > spec.getHiddenLayerCount())
			throw new IndexOutOfBoundsException("Invalid layer index: " + layer + ", allowed layer indexes range from 0 to the number of hidden layers in the neural network (" + spec.getHiddenLayerCount() + ")");
			
		if (layer != spec.getHiddenLayerCount() && (neuronIndex < 0 || neuronIndex >= spec.getHiddenLayerNeurons()))
			throw new IndexOutOfBoundsException("Invalid neuron index: " + neuronIndex + ", allowed neuron indexes range from 0 to the number of neurons on the hidden layers in the neural network minus 1 (" + (spec.getHiddenLayerNeurons()-1) + ")");
		
		if (layer == spec.getHiddenLayerCount() && (neuronIndex < 0 || neuronIndex >= spec.getOutputLayerNeurons()))
			throw new IndexOutOfBoundsException("Invalid neuron index: " + neuronIndex + ", allowed neuron indexes range from 0 to the number of neurons on the output layer in the neural network minus 1 (" + (spec.getOutputLayerNeurons()-1) + ")");
		

		if (layer == 0) {
			
			if ( weightIndex < 0 || weightIndex > spec.getInputLayerNeurons())
				throw new IndexOutOfBoundsException("Invalid weight index: " + weightIndex + ", allowed weight indexes range from 0 to the number of neurons on the input layer in the neural network minus 1 (" + (spec.getInputLayerNeurons()-1) + ")");

				
			return neuronIndex*spec.getInputLayerNeurons() + weightIndex;
		}
		else {
			if ( weightIndex < 0 || weightIndex > spec.getHiddenLayerNeurons())
				throw new IndexOutOfBoundsException("Invalid weight index: " + weightIndex + ", allowed weight indexes range from 0 to the number of neurons on the hidden layers in the neural network minus 1 (" + (spec.getHiddenLayerNeurons()-1) + ")");

			
			return hiddenValuesPos + (layer-1)*spec.getHiddenLayerNeurons()*spec.getHiddenLayerNeurons() + neuronIndex*spec.getHiddenLayerNeurons() + weightIndex; 
		}
	}
	
	
	public int getBiasIndex(int layer, int neuronIndex) {
		
		if (layer < 0 || layer > spec.getHiddenLayerCount())
			throw new IndexOutOfBoundsException("Invalid layer index: " + layer + ", allowed layer indexes range from 0 to the number of hidden layers in the neural network (" + spec.getHiddenLayerCount() + ")");

		if (layer != spec.getHiddenLayerCount() && (neuronIndex < 0 || neuronIndex >= spec.getHiddenLayerNeurons()))
			throw new IndexOutOfBoundsException("Invalid neuron index: " + neuronIndex + ", allowed neuron indexes range from 0 to the number of neurons on the hidden layers in the neural network minus 1 (" + (spec.getHiddenLayerNeurons()-1) + ")");
		
		if (layer == spec.getHiddenLayerCount() && (neuronIndex < 0 || neuronIndex >= spec.getOutputLayerNeurons()))
			throw new IndexOutOfBoundsException("Invalid neuron index: " + neuronIndex + ", allowed neuron indexes range from 0 to the number of neurons on the output layer in the neural network minus 1 (" + (spec.getOutputLayerNeurons()-1) + ")");

		
		return layer*spec.getHiddenLayerCount() +  neuronIndex;
	}
	

	public int getLayerCount() {
		return spec.getHiddenLayerCount() + 1;
	}
}
