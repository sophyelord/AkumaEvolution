package population.extensions;

import population.Individual;

public interface EditableIndividual<OBJ> extends Individual<OBJ>{

	public void setScore(float score);
}
