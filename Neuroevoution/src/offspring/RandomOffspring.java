package offspring;

import java.util.Random;

import population.Generation;
import population.Population;

public class RandomOffspring<OBJ> implements OffspringGenerator<OBJ>{

	Population<OBJ> population;
	Generation<OBJ> generation;
	Random r;
	@Override
	public void init() {
		
		r = new Random();
		
	}

	@Override
	public void generateOffspring() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Population<OBJ> getPopulation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGeneration(Generation<OBJ> population) {
		// TODO Auto-generated method stub
		
	}

}
