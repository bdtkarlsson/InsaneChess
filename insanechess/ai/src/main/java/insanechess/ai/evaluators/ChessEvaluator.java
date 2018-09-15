package insanechess.ai.evaluators;

import insanechess.backend.InsaneChessPosition;

public interface ChessEvaluator {
	double evaluate(InsaneChessPosition model);
}
