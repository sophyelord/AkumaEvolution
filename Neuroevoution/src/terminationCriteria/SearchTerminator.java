package terminationCriteria;

public interface SearchTerminator<OBJ> {

	public void evaluateCriterion();
	
	public boolean searchOver();
}
