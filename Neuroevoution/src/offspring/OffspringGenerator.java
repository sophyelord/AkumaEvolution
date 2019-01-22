package offspring;

import tasks.SearchTask;

public interface OffspringGenerator<OBJ> {

	public SearchTask generateOffspring();
	
}
