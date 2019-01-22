package evaluation;

public interface NumericEvaluator<OBJ extends Number> extends Runnable {

	public void setVector(OBJ[] vector);
	public float getScore();
}
