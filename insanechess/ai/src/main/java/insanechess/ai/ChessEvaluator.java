package insanechess.ai;

import insanechess.backend.InsaneChessPosition;

public interface ChessEvaluator {
	double evaluate(InsaneChessPosition model);
}
