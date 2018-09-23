package insanechess.game;

import insanechess.ai.algorithms.ChessAlgorithm;
import insanechess.ai.evaluators.InsaneChessEvaluator;
import insanechess.ai.algorithms.NegaMaxAlphaBetaAlgorithm;
import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;

import static insanechess.backend.constants.Player.BLACK;
import static insanechess.backend.constants.Player.WHITE;

public class InsaneChessGame {

	private InsaneChessSettings settings;
	private ChessPosition model;
	private ChessAlgorithm whiteAI;
	private ChessAlgorithm blackAI;
	private int movesSinceLastAttack;
	private GameThread gameThread;

	InsaneChessGame() {
		this(new InsaneChessSettings());
	}

	InsaneChessGame(InsaneChessSettings settings) {
		this.settings = settings;
		movesSinceLastAttack = 0;
		whiteAI = new NegaMaxAlphaBetaAlgorithm(new InsaneChessEvaluator(), settings.getWhiteAIDepth());
		blackAI = new NegaMaxAlphaBetaAlgorithm(new InsaneChessEvaluator(), settings.getBlackAIDepth());
		model = new ChessPosition();
		gameThread = new GameThread();
	}

	ChessPosition getModel() {
		return model;
	}

	InsaneChessSettings getSettings() {
		return settings;
	}

	synchronized void makeMove(ChessMove move) {

		ChessPosition newModel = getModel().makeMove(move);
		if(newModel != null) {
			int nrOfPieces = getModel().getOpponentPieces(getModel().getPlayerToMove()).cardinality();
			this.model = newModel;
			if(nrOfPieces == getModel().getAlliedPieces(getModel().getPlayerToMove()).cardinality()) {
				movesSinceLastAttack++;
			} else {
				movesSinceLastAttack = 0;
			}
		}
	}

	boolean isRunning() {
		return gameThread != null && gameThread.running;
	}

	synchronized void startThread() {
		stopThread();
		gameThread = new GameThread();
		gameThread.start();
	}

	synchronized  void stopThread() {
		gameThread.stopThread();
	}

	private class GameThread extends Thread {

		private boolean running = false;

		@Override
		public void run() {
			try {
				running = true;
				while (running) {
					if (model.getPlayerToMove() == WHITE && settings.getWhiteAIOn()) {
						ChessMove move = whiteAI.evaluate(model);
						if (move != null) {
							makeMove(move);
						}

					} else if (model.getPlayerToMove() == BLACK && settings.getBlackAIOn()) {
						ChessMove move = blackAI.evaluate(model);
						if (move != null) {
							makeMove(move);
						}
					}

				}
			} catch (NullPointerException e) {
				running = false;
			}
		}

		void stopThread() {
			running = false;
		}
	}


	int getMovesSinceLastAttack() {
		return movesSinceLastAttack;
	}

	public static void main(String[] args) {
		new InsaneChessController();
	}

}
