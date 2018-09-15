package insanechess.ai.algorithms;

import insanechess.ai.evaluators.ChessEvaluator;
import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;

public interface ChessAlgorithm {
	void setEvaluator(ChessEvaluator evaluator);
	ChessMove evaluate(InsaneChessPosition position);
	void setSearchDepth(int searchDepth);
}
