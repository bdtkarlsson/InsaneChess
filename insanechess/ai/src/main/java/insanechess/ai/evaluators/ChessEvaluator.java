package insanechess.ai.evaluators;

import insanechess.backend.ChessPosition;

public interface ChessEvaluator {
	double evaluate(ChessPosition model);
}
