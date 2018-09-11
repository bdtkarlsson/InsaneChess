package insanechess.frontend;

import insanechess.game.InsaneChessController;

import javax.swing.*;
import java.awt.*;

import static insanechess.backend.constants.Player.BLACK;
import static insanechess.backend.constants.Player.WHITE;

class SettingsWindow {
	
	private static final int MAX_DEPTH = 10;
	private static final int MIN_DEPTH = 1;
	
	static void display(InsaneChessController controller) {

		int whiteDepth = controller.getSearchDepth(true);
		int blackDepth = controller.getSearchDepth(false);
		boolean whiteAI = controller.isAIOn(WHITE);
		boolean blackAI = controller.isAIOn(BLACK);

		whiteDepth = adjustDepth(whiteDepth);
		blackDepth = adjustDepth(blackDepth);


		JCheckBox whiteAIBox = new JCheckBox("White AI", whiteAI);
		JCheckBox blackAIBox = new JCheckBox("Black AI", blackAI);

		JSlider whiteSlider = getSlider(whiteDepth);
		JSlider blackSlider = getSlider(blackDepth);

		JPanel panel = getPanel(whiteAIBox, blackAIBox, whiteSlider, blackSlider);

		int result = JOptionPane.showConfirmDialog(null, panel, "Settings",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			controller.setSearchDepth(true, whiteSlider.getValue());
			controller.setSearchDepth(false, blackSlider.getValue());
			controller.setAIOn(true, whiteAIBox.isSelected());
			controller.setAIOn(false, blackAIBox.isSelected());
		}
	}

	private static int adjustDepth(int whiteDepth) {
		if (whiteDepth > MAX_DEPTH) {
			whiteDepth = MAX_DEPTH;
		} else if (whiteDepth < MIN_DEPTH) {
			whiteDepth = MIN_DEPTH;
		}
		return whiteDepth;
	}

	private static JPanel getPanel(JCheckBox whiteAIBox, JCheckBox blackAIBox, JSlider whiteSlider, JSlider blackSlider) {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(whiteAIBox );
		panel.add(blackAIBox );
		panel.add(new JLabel("White AI search depth"));
		panel.add(whiteSlider);
		panel.add(new JLabel("Black AI search depth"));
		panel.add(blackSlider);
		return panel;
	}

	private static JSlider getSlider(int whiteDepth) {
		JSlider whiteSlider = new JSlider(MIN_DEPTH, MAX_DEPTH);
		whiteSlider.setPaintTicks(true);
		whiteSlider.setPaintLabels(true);
		whiteSlider.setMajorTickSpacing(MIN_DEPTH);
		whiteSlider.setMinorTickSpacing(MAX_DEPTH);
		whiteSlider.setValue(whiteDepth);
		return whiteSlider;
	}
}
