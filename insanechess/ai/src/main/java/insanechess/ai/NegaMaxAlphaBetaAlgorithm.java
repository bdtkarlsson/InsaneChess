package insanechess.ai;

import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;

import java.util.Random;

import static insanechess.backend.constants.Player.WHITE;

public class NegaMaxAlphaBetaAlgorithm implements ChessAlgorithm {

	private ChessEvaluator evaluator;
	private int searchDepth;
	private ChessMove bestMove;
	private Random rand;
	private long positionsSearched;

	public NegaMaxAlphaBetaAlgorithm(ChessEvaluator evaluator, int searchDepth) {
		this.searchDepth = searchDepth;
		this.evaluator = evaluator;
		rand = new Random();
	}

	@Override
	public ChessMove evaluate(InsaneChessPosition position) {
		positionsSearched = 0;
		negaMax(position, searchDepth, Double.MIN_VALUE, Double.MAX_VALUE, true);
		//System.out.println("NegaMaxAlphaBeta positions searched: " + positionsSearched);
		return bestMove;
	}

	private double negaMax(InsaneChessPosition position, int depth, double alpha, double beta, boolean root) {
		positionsSearched++;
		if (depth <= 0 || position.getLegalMoves(position.getPlayerToMove()).size() == 0) {
			double val = evaluator.evaluate(position);
			return position.getPlayerToMove() == WHITE ? val : -val;
		}
		double bestValue = Long.MIN_VALUE;
		for (ChessMove move : position.getLegalMoves(position.getPlayerToMove())) {
			InsaneChessPosition childPosition = position.makeMove(move);
			if(childPosition == null) {
				continue;
			}
			double value = -negaMax(childPosition, depth - 1, -beta, -alpha, false);
			if (value > bestValue || value == bestValue && rand.nextBoolean()) {
				bestValue = value;
				if (root) {
					move.setValue(bestValue);
					bestMove = move;
				}
			}
			alpha = Math.max(alpha, value);
			if (alpha >= beta) {
				break;
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
