package population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BasicPopulation<OBJ> implements Population<OBJ>, PopulationAnalyzer<OBJ>{
	
	List<Individual<OBJ>> individuals;
	
	Individual<OBJ> maxScore;
	Individual<OBJ> minScore;
	
	float meanScore;
	boolean computedMean;
	
	boolean ordered;
	
	public BasicPopulation(List<OBJ> individuals) {
		
		this.individuals = new ArrayList<>();
		
		
		for (int i = 0 ; i < individuals.size() ; i++) {
			this.individuals.add(new BasicIndividual<OBJ>(individuals.get(i)));
		}
		
		maxScore = null;
		minScore = null;
		computedMean = false;
		ordered = false;
	}
	
	
	@Override
	public Iterator<Individual<OBJ>> iterator() {
		return individuals.iterator();
	}


	@Override
	public Individual<OBJ> getMaxScoreIndividual() {
		
		if (maxScore == null) {
			
			float max = Float.NEGATIVE_INFINITY;
			
			for (Individual<OBJ> ind : individuals) {
				
				
				if (ind.getScore() > max) {
					max = ind.getScore();
					maxScore = ind;
				}
				
				
			}
			
			
		}
		
		return maxScore;
	}


	@Override
	public Individual<OBJ> getMinScoreIndividual() {
		if (minScore == null) {
			
			float min = Float.POSITIVE_INFINITY;
			
			for (Individual<OBJ> ind : individuals) {
				
				
				if (ind.getScore() < min) {
					min = ind.getScore();
					minScore = ind;
				}
				
				
			}
			
			
		}
		
		return minScore;
	}


	@Override
	public float getMeanScore() {
		
		if (!computedMean) {
			
			
			
			for (Individual<OBJ> ind : individuals) {
				
				
				meanScore += ind.getScore();
				
				
			}
			
			meanScore /= getPopulationSize();
			computedMean = true;
		}
		
		return meanScore;
	}


	@Override
	public int getPopulationSize() {
		// TODO Auto-generated method stub
		return individuals.size();
	}


	@Override
	public Iterator<Individual<OBJ>> getInOrderIterator() {
		
		if (!ordered) {
			Collections.sort(individuals, new Comparator<Individual<OBJ>>() {
	
				@Override
				public int compare(Individual<OBJ> arg0, Individual<OBJ> arg1) {
					if ((arg1.getScore() - arg0.getScore()) < 0)
						return -1;
					else if ((arg1.getScore() - arg0.getScore()) > 0)
						return 1;
					else
						return 0;
				}
			});
		}
		return individuals.iterator();
	}

}
