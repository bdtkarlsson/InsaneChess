package insanechess.ai.algorithms;

import insanechess.ai.evaluators.ChessEvaluator;
import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;

import java.util.Random;

import static insanechess.backend.constants.Player.WHITE;

public class NegaMaxAlgorithm implements ChessAlgorithm {

	private ChessEvaluator evaluator;
	private int searchDepth;
	private ChessMove bestMove;
	private Random rand;
	private long positionsSearched;

	public NegaMaxAlgorithm(ChessEvaluator evaluator, int searchDepth) {
		this.searchDepth = searchDepth;
		this.evaluator = evaluator;
		rand = new Random();
	}

	@Override
	public ChessMove evaluate(ChessPosition position) {
        positionsSearched = 0;
		negaMax(position, searchDepth, true);
		return bestMove;
	}

	private double negaMax(ChessPosition position, int depth, boolean root) {
	    positionsSearched++;
		if (depth <= 0 || position.getLegalMoves(position.getPlayerToMove()).size() == 0) {
            double val = evaluator.evaluate(position);
            return position.getPlayerToMove() == WHITE ? val : -val;
		}
		double bestValue = Long.MIN_VALUE;
		for (ChessMove move : position.getLegalMoves(position.getPlayerToMove())) {
			ChessPosition childPosition = position.makeMove(move);
			if(childPosition == null) {
				continue;
			}
			double value = -negaMax(childPosition, depth - 1, false);
			if (value > bestValue || value == bestValue && rand.nextBoolean()) {
				bestValue = value;
				if (root) {
					move.setValue(bestValue);
					bestMove = move;
				}
			}
		}
		return bestValue;
	}


    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    public void setEvaluator(ChessEvaluator evaluator) {
        this.evaluator = evaluator;
    }
}
