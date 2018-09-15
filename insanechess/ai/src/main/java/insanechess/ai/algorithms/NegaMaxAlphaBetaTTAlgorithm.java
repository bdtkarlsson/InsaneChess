package insanechess.ai.algorithms;

import insanechess.ai.EntryFlag;
import insanechess.ai.TranspositionTableEntry;
import insanechess.ai.evaluators.ChessEvaluator;
import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static insanechess.backend.constants.Player.WHITE;

public class NegaMaxAlphaBetaTTAlgorithm implements ChessAlgorithm {

	private ChessEvaluator evaluator;
	private int searchDepth;
	private ChessMove rootBestMove;
	private Random rand;
	private long positionsSearched;
	private Map<InsaneChessPosition, TranspositionTableEntry> transpositionTable;

	public NegaMaxAlphaBetaTTAlgorithm(ChessEvaluator evaluator, int searchDepth) {
		this.searchDepth = searchDepth;
		this.evaluator = evaluator;
		rand = new Random();
		transpositionTable = new HashMap<>();
	}

	@Override
	public ChessMove evaluate(InsaneChessPosition position) {
		positionsSearched = 0;
		negaMax(position, searchDepth, Double.MIN_VALUE, Double.MAX_VALUE, true);
		return rootBestMove;
	}

	private double negaMax(InsaneChessPosition position, int depth, double alpha, double beta, boolean root) {

		double alphaOrig = alpha;
        if (!root) {
            TranspositionTableEntry entry = transpositionTable.get(position);
            if (entry != null && entry.getDepth() >= depth) {
                switch (entry.getFlag()) {
                    case EXACT:
                        return entry.getValue();
                    case LOWER:
                        alpha = Math.max(alpha, entry.getValue());
                        break;
                    case UPPER:
                        beta = Math.min(beta, entry.getValue());
                        break;
                }
                if (alpha >= beta) {
                    return entry.getValue();
                }
            }
        }
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
					rootBestMove = move;
				}
			}
			alpha = Math.max(alpha, value);
			if (alpha >= beta) {
				break;
			}
		}

		if (!root) {
			TranspositionTableEntry newEntry = new TranspositionTableEntry(depth, bestValue,
					getEntryFlag(alphaOrig, beta, bestValue), System.currentTimeMillis());
			putInTable(position, newEntry);
		}

		return bestValue;
	}


	private void putInTable(InsaneChessPosition position, TranspositionTableEntry entry) {
		TranspositionTableEntry existingEntry = transpositionTable.get(position);
		if (existingEntry == null) {
			transpositionTable.put(position, entry);
		}
		else if (entry.getDepth() > existingEntry.getDepth()) {
			transpositionTable.put(position, entry);
		}
	}

	private EntryFlag getEntryFlag(double a, double b, double v) {
		if (v <= a) {
			return EntryFlag.UPPER;
		}
		else if (v >= b) {
			return EntryFlag.LOWER;
		}
		else {
			return EntryFlag.EXACT;
		}
	}

	public void setSearchDepth(int searchDepth) {
		this.searchDepth = searchDepth;
	}

	public void setEvaluator(ChessEvaluator evaluator) {
		this.evaluator = evaluator;
	}
}
