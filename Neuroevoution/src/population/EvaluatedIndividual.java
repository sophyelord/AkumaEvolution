package population;

public class EvaluatedIndividual<OBJ> {

	private OBJ individual;
	private float score;
	
	public EvaluatedIndividual(OBJ individual, float score){
	
		this.individual = individual;
		this.score = score;
		
	}
	
	public OBJ getIndividual() {
		return individual;
	}
	
	public float getScore() {
		return score;
	}
}
