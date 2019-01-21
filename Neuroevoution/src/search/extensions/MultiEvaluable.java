package search.extensions;

import evaluation.Evaluator;

public interface MultiEvaluable<OBJ> {

	public void setEvaluator(Evaluator<OBJ> eval);
}
