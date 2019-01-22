package population;

import java.util.Iterator;

public interface PopulationAnalyzer<OBJ> {

	public Individual<OBJ> getMaxScoreIndividual();
	public Individual<OBJ> getMinScoreIndividual();
	public float getMeanScore();
	public int getPopulationSize();
	public Iterator<Individual<OBJ>> getInOrderIterator();
	
}
