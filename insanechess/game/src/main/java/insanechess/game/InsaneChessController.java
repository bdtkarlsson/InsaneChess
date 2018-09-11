package insanechess.game;

import insanechess.backend.ChessConstants.Player;
import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;

public class InsaneChessController{
	private InsaneChessGame game;

	public InsaneChessController() {
		this.game = new InsaneChessGame();
	}

	public synchronized void startNewGame() {
		InsaneChessSettings settings = game != null ? game.getSettings() : new InsaneChessSettings();
		game = new InsaneChessGame(settings);
		game.startThread();
	}

	public synchronized Player playerToMove() {
		return game.getModel().getPlayerToMove();
	}

	public synchronized void makeMove(ChessMove move) {
		game.makeMove(move);
	}

	public synchronized InsaneChessPosition  getCurrentPosition() {
		return game.getModel();
	}

	public synchronized boolean isAIOn(Player player) {
		if(player == Player.WHITE) {
			return game.getSettings().getWhiteAIOn();
		} else {
			return game.getSettings().getBlackAIOn();
		}
	}

	public synchronized void setAIOn(boolean white, boolean on) {
		if(white) {
			game.getSettings().setWhiteAIOn(on);
		} else {
			game.getSettings().setBlackAIOn(on);
		}
	}

	public synchronized void stopGame() {
		game.stopThread();
	}

	public synchronized int getSearchDepth(boolean white) {
		if(white) {
			return game.getSettings().getWhiteAIDepth();
		} else {
			return game.getSettings().getBlackAIDepth();
		}
	}

	public synchronized void setSearchDepth(boolean white, int depth) {
		if(white) {
			game.getSettings().setWhiteAIDepth(depth);
		} else {
			game.getSettings().setBlackAIDepth(depth);
		}
	}

	public int getMovesSinceLastAttack() {
		return game.getMovesSinceLastAttack();
	}

	public InsaneChessSettings getSettings() {
		return game.getSettings();
	}

	public boolean isGameRunning() {
		return game.isRunning();
	}
}
