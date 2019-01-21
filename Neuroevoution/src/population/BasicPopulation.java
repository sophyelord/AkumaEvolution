package population;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasicPopulation<OBJ> implements Population<OBJ>{
	
	List<Individual<OBJ>> individuals;
	
	public BasicPopulation(List<OBJ> individuals) {
		
		this.individuals = new ArrayList<>();
		
		
		for (int i = 0 ; i < individuals.size() ; i++) {
			this.individuals.add(new BasicIndividual<OBJ>(individuals.get(i)));
		}
	}
	
	
	@Override
	public Iterator<Individual<OBJ>> iterator() {
		return individuals.iterator();
	}

}
