package neuralnetworks;
import activationsquisher.ActivationSquisher;
import exceptions.ErroneousNeuralNetworkSpecException;

public class NeuralNetworkSpec {

	private int hiddenLayerCount;
	private int hiddenLayerNeurons;
	private int inputLayerNeurons;
	private int outputLayerNeurons;
	
	private ActivationSquisher squisher;
	
	public NeuralNetworkSpec(int hiddenLayerCount, int hiddenLayerNeurons, int inputLayerNeurons,
			int outputLayerNeurons, ActivationSquisher squisher){
		
		super();
		
		if (squisher == null)
			throw new NullPointerException("Null ActivationSquisher reference");
		
		checkParameterIntegrity(hiddenLayerCount, hiddenLayerNeurons, inputLayerNeurons, outputLayerNeurons);
		
		
		this.hiddenLayerCount = hiddenLayerCount;
		this.hiddenLayerNeurons = hiddenLayerNeurons;
		this.inputLayerNeurons = inputLayerNeurons;
		this.outputLayerNeurons = outputLayerNeurons;
		this.squisher = squisher;
	}
	

	public int getHiddenLayerCount() {
		return hiddenLayerCount;
	}

	public int getHiddenLayerNeurons() {
		return hiddenLayerNeurons;
	}

	public int getInputLayerNeurons() {
		return inputLayerNeurons;
	}

	public int getOutputLayerNeurons() {
		return outputLayerNeurons;
	}

	public ActivationSquisher getSquisher() {
		return squisher;
	}
	
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		str.append("Input layer neurons: " + inputLayerNeurons + "\n");
		str.append("Output layer neurons: " + outputLayerNeurons + "\n");
		str.append("Hidden layer count: " + hiddenLayerCount + "\n");
		str.append("Hidden layer neurons: " + hiddenLayerNeurons + "\n");
		
		return str.toString();
	}
	
	private void checkParameterIntegrity(int hiddenLayerCount, int hiddenLayerNeurons, int inputLayerNeurons, int outputLayerNeurons){
	
		if (hiddenLayerCount < 0 || hiddenLayerNeurons < 0 || inputLayerNeurons < 0 || outputLayerNeurons <0)
			throw new ErroneousNeuralNetworkSpecException("Negative values are not allowed");
		
		if (hiddenLayerCount != 0 && hiddenLayerNeurons == 0)
			throw new ErroneousNeuralNetworkSpecException("Hidden layer neurons cannot be 0 unless no hidden layers");
		
		if (inputLayerNeurons == 0 || outputLayerNeurons == 0)
			throw new ErroneousNeuralNetworkSpecException("Input and output layer neuron count must be non-zero");
	}
}
