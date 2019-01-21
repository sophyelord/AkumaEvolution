package population;

import population.extensions.EditableIndividual;

public class BasicIndividual<OBJ> implements EditableIndividual<OBJ> {

	private OBJ obj;
	private float score;
	
	public BasicIndividual(OBJ obj) {
		this.obj = obj;
		this.score = Float.NEGATIVE_INFINITY;
	}
	
	@Override
	public OBJ getIndividual() {
		return obj;
	}

	@Override
	public float getScore() {
		return score;
	}

	@Override
	public void setScore(float score) {
		this.score = score;
		
	}

}
