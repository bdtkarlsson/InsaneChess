package insanechess.ai;

import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;

public interface ChessAlgorithm {
	void setEvaluator(ChessEvaluator evaluator);
	ChessMove evaluate(InsaneChessPosition position);
	void setSearchDepth(int searchDepth);
}
