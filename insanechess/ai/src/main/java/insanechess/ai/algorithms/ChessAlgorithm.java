package insanechess.ai.algorithms;

import insanechess.ai.evaluators.ChessEvaluator;
import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;

public interface ChessAlgorithm {
	void setEvaluator(ChessEvaluator evaluator);
	ChessMove evaluate(ChessPosition position);
	void setSearchDepth(int searchDepth);
}
