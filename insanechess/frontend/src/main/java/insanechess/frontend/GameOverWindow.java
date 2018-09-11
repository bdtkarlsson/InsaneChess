package insanechess.frontend;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class GameOverWindow {

	static void display(String text) {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel(text));
		JOptionPane.showConfirmDialog(null, panel, "Game over",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
}
