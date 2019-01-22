package evaluation;

import population.Individual;
import population.extensions.EditableIndividual;

public class NumericEvaluatorImp<OBJ extends Number> implements IndividualEvaluator<OBJ[]> {

	private EditableIndividual<OBJ[]> individual;
	
	private NumericEvaluator<OBJ> eval;
	
	
	public NumericEvaluatorImp(NumericEvaluator<OBJ> eval) {
		this.eval = eval;
	}
	
	public void setNumericEvaluator(NumericEvaluator<OBJ> eval) {
		this.eval = eval;
	}
	
	@Override
	public void run() {
		
		OBJ[] obj = individual.getIndividual();
		
		eval.setVector(obj);
		eval.run();
		
		float sc = eval.getScore();
		individual.setScore(sc);
		
	}


	@Override
	public void setIndividual(Individual<OBJ[]> individual) {
		
		if (individual instanceof EditableIndividual<?>)
			this.individual = (EditableIndividual<OBJ[]>) individual;
		else
			throw new EditableIndividualNotImplemented();
		
	}

}
