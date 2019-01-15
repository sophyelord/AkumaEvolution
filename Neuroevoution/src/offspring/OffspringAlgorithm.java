package offspring;

import objectInterface.ObjectToolkit;

public interface OffspringAlgorithm<OBJ> {

	public OffspringGenerator<OBJ> bindToolkit(ObjectToolkit<OBJ> toolkit) throws ToolkitInsuficientException;
	
	public Class<?>[] getToolkitImplementationRequirements();
	
}
