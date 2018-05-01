package geneticEvolution.optimizablegenerations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import geneticEvolution.OptimizableGeneration;

public class BasicOptimizableGeneration<T> implements OptimizableGeneration<T> {

	private List<T> optimizableList;
	private List<Float> fitnessList;
	
	private List<Integer> rankingList;
	private Map<T,Integer> optimizableMap;
	
	private boolean ranked;
	
	public BasicOptimizableGeneration(List<T> optimizableList) {
		
		this.optimizableList = optimizableList;
		this.fitnessList = new ArrayList<Float>();
		this.rankingList = new ArrayList<Integer>();
		this.optimizableMap = new HashMap<T,Integer>();
		
		
		this.ranked = false;
		for (int i = 0 ; i < optimizableList.size() ; i++) {
			fitnessList.add(0.0f);
			rankingList.add(i);
			optimizableMap.put(optimizableList.get(i),i);
		}
		
		this.ranked = false;
	}
	
	@Override
	public Iterator<T> iterator() {
		return optimizableList.iterator();
	}

	@Override
	public void setFitness(T optimizable, float fitness) {

		fitnessList.set(optimizableMap.get(optimizable), fitness);
		this.ranked = false;
	}
	
	@Override
	public T getIBestOptimizable(int i) {
	
		if (!ranked)
			rank();
		
		return optimizableList.get(rankingList.get(i));
	}

	private void rank() {
		
		rankingList = sortRankingList(0,optimizableList.size());
		ranked = true;
	}

	
	private List<Integer> sortRankingList(int min, int max) {
		
		if (max - min <= 1) {
			List<Integer> temp = new ArrayList<Integer>();
			temp.add(rankingList.get(min));
			return temp;
		}
		else {
			
			List<Integer> a = sortRankingList(min,(max+min)/2);
			List<Integer> b = sortRankingList((max+min)/2,max);
			
			return mergeRankingList(a,b);
			
		}
				
	}
	
	

	private List<Integer> mergeRankingList(List<Integer> a, List<Integer> b) {

		List<Integer> temp = new ArrayList<Integer>(a.size()+b.size());
		
		int i = 0;
		int j = 0;
		
		while (i < a.size() && j < b.size()) {
			
			if (fitnessList.get(a.get(i)) > fitnessList.get(b.get(j))) {
				temp.add(a.get(i));
				i++;
			}
			else {
				temp.add(b.get(j));
				j++;
			}
			
		}
		
		if (i == a.size()) {
			for (; j < b.size(); j++) {
				temp.add(b.get(j));
			}
		}
		else{
			
			for (; i < a.size(); i++) {
				temp.add(a.get(i));
			}
		}
			
		
		return temp;
		
	}

	@Override
	public float getIBestFitness(int i) {
		if (!ranked)
			rank();
		
		return fitnessList.get(rankingList.get(i));
	}

	@Override
	public int getIndividualCount() {
		
		return optimizableList.size();
	}

}
