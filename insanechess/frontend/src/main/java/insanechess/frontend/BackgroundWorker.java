package insanechess.frontend;

import insanechess.backend.ChessConstants;
import insanechess.game.InsaneChessController;

import javax.swing.*;

public class BackgroundWorker extends SwingWorker<Integer, String> {

    private static final int FPS = 60;
    private InsaneChessController chessController;
    private ChessGUI chessGUI;

    BackgroundWorker(InsaneChessController chessController, ChessGUI chessGUI) {
        this.chessController = chessController;
        this.chessGUI = chessGUI;
    }

    @Override
    protected Integer doInBackground() {
        double timesPerTick = 1000000000d / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        boolean running = true;
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timesPerTick;
            timer += (now - lastTime);
            lastTime = now;
            if(delta >= 1) {
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                if(chessController.isGameRunning()) {
                    isGameOver();
                }
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }

        }
        return 0;
    }

    private void render() {
        chessGUI.updateChessboard(chessController.getCurrentPosition());
    }

    private void isGameOver() {
        int mate = chessController.getCurrentPosition().isMate(chessController.playerToMove());
        if(mate == 1 || mate == 2 || chessController.getMovesSinceLastAttack() > chessController.getSettings().getMaxMovesWithoutAttack()) {
            String endgameText = "Game over: ";
            if(mate == 1) {
                endgameText += "Game ended with checkmate. ";
                if(chessController.playerToMove() == ChessConstants.Player.WHITE) {
                    endgameText += "Black wins.";
                } else {
                    endgameText += "White wins.";
                }
            } else if(mate == 2){
                endgameText += "Game ended with a stalemate";
            } else {
                endgameText += "No piece taken for " + chessController.getSettings().getMaxMovesWithoutAttack() + " turns";
            }
            chessGUI.stopGame(chessController);
            chessGUI.displayGameOverWindow(endgameText);
        }
    }
}
