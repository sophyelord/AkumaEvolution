package activationsquisher;

public class LogisticSquisher implements ActivationSquisher {

	@Override
	public float squish(float real) {
		return (float) (1 / (1 + Math.exp(-real)));
	}

}
