package insanechess.frontend;

import insanechess.backend.InsaneChessPosition;
import insanechess.game.InsaneChessController;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChessGUI {

	private ChessBoardPanel chessboardPanel;
	private JMenuItem stopGameMenu;
	private JMenuItem settingsMenu;

	private ChessGUI(InsaneChessController controller) {

		//Set up frame
		Runnable frameInitializer = () -> {
			JFrame mainFrame = new JFrame("Insane Chess");
			chessboardPanel = createChessboardPanel(controller);
			JPanel mainPanel = createMainPanel(chessboardPanel);
			mainFrame.add(mainPanel);

			JMenuBar menuBar = new JMenuBar();
			JMenu menu = createMenu(controller);
			menuBar.add(menu);
			mainFrame.setJMenuBar(menuBar);

			/*Ensures JVM closes after frame(s) closed and
			 all non-daemon threads are finished */
			mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			mainFrame.setLocationByPlatform(true);

			/*ensures the frame is the minimum size it needs to be
			in order display the components within it */
			mainFrame.pack();
			/*ensures the minimum size is enforced.*/
			mainFrame.setMinimumSize(mainFrame.getSize());
			mainFrame.setVisible(true);
		};
		/*Swing GUIs should be created and updated on the EDT
		 http://docs.oracle.com/javase/tutorial/uiswing/concurrency */
		SwingUtilities.invokeLater(frameInitializer);
		new BackgroundWorker(controller, this).execute();
	}

	@NotNull
	private JMenu createMenu(InsaneChessController controller) {
		JMenu menu = new JMenu("Insane Chess");
		JMenuItem newGameMenu = createNewGameMenu(controller);
		settingsMenu = createSettingsMenu(controller);
		stopGameMenu = createStopGameMenu(controller);
		menu.add(newGameMenu);
		menu.add(stopGameMenu);
		menu.add(settingsMenu);
		return menu;
	}

	private JMenuItem createStopGameMenu(InsaneChessController controller) {
		JMenuItem menuStopGame = new JMenuItem("Stop game");
		menuStopGame.setEnabled(false);
		menuStopGame.addActionListener(e -> stopGame(controller));
		return menuStopGame;
	}

	private JMenuItem createSettingsMenu(InsaneChessController controller) {
		JMenuItem menuSettings = new JMenuItem("Settings");
		menuSettings.addActionListener(e -> SettingsWindow.display(controller));
		return menuSettings;
	}

	private JMenuItem createNewGameMenu(InsaneChessController controller) {
		JMenuItem menuNewGame = new JMenuItem("New game");
		menuNewGame.addActionListener(e -> startGame(controller));
		return menuNewGame;
	}

	private void startGame(InsaneChessController controller) {
		chessboardPanel.setClickingActivated(true);
		stopEnabled(true);
		settingsEnabled(false);
		controller.startNewGame();
	}

	void stopGame(InsaneChessController controller) {
		chessboardPanel.setClickingActivated(false);
		stopEnabled(false);
		settingsEnabled(true);
		controller.stopGame();
	}

	private ChessBoardPanel createChessboardPanel(InsaneChessController controller) {
		return new ChessBoardPanel(controller);
	}

	private JPanel createMainPanel(JPanel chessboardPanel) {
		JPanel mainPanel = new JPanel(new BorderLayout(3, 3));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JPanel boardConstrain = new JPanel(new GridBagLayout());
		boardConstrain.setBackground(Color.white);
		boardConstrain.add(chessboardPanel);
		mainPanel.add(boardConstrain);
		return mainPanel;
	}

	synchronized void updateChessboard(InsaneChessPosition currentPosition) {
		Runnable r = () -> {
			chessboardPanel.updateBoard(currentPosition);
			chessboardPanel.repaint();
		};
		/*Swing GUIs should be created and updated on the EDT
		 http://docs.oracle.com/javase/tutorial/uiswing/concurrency */
		SwingUtilities.invokeLater(r);
	}

	private synchronized void settingsEnabled(boolean enabled) {
		settingsMenu.setEnabled(enabled);
	}

	private synchronized void stopEnabled(boolean enabled) {
		stopGameMenu.setEnabled(enabled);
	}

	public static void main(String[] args) {
		InsaneChessController insaneChessController = new InsaneChessController();
		ChessGUI chessGUI = new ChessGUI(insaneChessController);
		chessGUI.updateChessboard(insaneChessController.getCurrentPosition());
	}

	void displayGameOverWindow(String text) {
		GameOverWindow.display(text);
	}

}