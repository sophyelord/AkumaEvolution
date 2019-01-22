package evaluation.samples;

import evaluation.NumericEvaluator;

public class SumNumericEvaluator<OBJ extends Number> implements NumericEvaluator<OBJ>{

	private OBJ[] vec;
	private float score;
	
	@Override
	public void run() {
		
		score = 0;
		for (OBJ n : vec){
			score += (float)n;
		}		
		
	}

	@Override
	public void setVector(OBJ[] vector) {
		this.vec = vector;
		
	}

	@Override
	public float getScore() {
		return this.score;
	}

	
}
