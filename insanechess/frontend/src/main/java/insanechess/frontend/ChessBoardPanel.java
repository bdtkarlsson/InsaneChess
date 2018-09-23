package insanechess.frontend;

import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;
import insanechess.game.InsaneChessController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.BitSet;

import static insanechess.backend.constants.Player.*;
import static insanechess.backend.constants.Player.WHITE;
import static insanechess.backend.constants.Validations.getIndexAtPosition;
import static insanechess.backend.constants.Validations.getPositionAtIndex;

public class ChessBoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String COLS = "ABCDEFGH";
	private InsaneChessController controller;
	private JButton[][] chessboardSquares = new JButton[8][8];
	private Image[][] chessPieceImages = new Image[2][6];
	private Point markedSquare;


	ChessBoardPanel(InsaneChessController controller) {
		this.controller = controller;
		this.setLayout(new GridLayout(0, 9));
		setBorder(new CompoundBorder(
				new EmptyBorder(8,8,8,8),
				new LineBorder(Color.BLACK)
				));
		// Set the BG to be ochre
		Color backgroundColor = Color.white;
		setBackground(backgroundColor);
		createImages();
		createChessboardSquares();
		fillChessboard();
		setClickingActivated(false);
	}

	/**
	 * Override the preferred size to return the largest it can, in
	 * a square shape.  Must (must, must) be added to a GridBagLayout
	 * as the only component (it uses the parent as a guide to size)
	 * with no GridBagConstaint (so it is centered).
	 */
	@Override
	public final Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		Dimension prefSize;
		Component c = getParent();
		if (c == null) {
			prefSize = new Dimension(
					(int)d.getWidth(),(int)d.getHeight());
		} else if (c.getWidth()>d.getWidth() && c.getHeight()>d.getHeight()) {
			prefSize = c.getSize();
		} else {
			prefSize = d;
		}
		int w = (int) prefSize.getWidth();
		int h = (int) prefSize.getHeight();
		// the smaller of the two sizes
		int s = (w>h ? h : w);
		return new Dimension(s,s);
	}

	private void createChessboardSquares() {
		// create the chess board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int y = 0; y < chessboardSquares.length; y++) {
			for (int x = 0; x < chessboardSquares[y].length; x++) {
				JButton b = new JButton();
				final Point p = new Point(x, y);
				/*Add an actions listener to each square. Handles the move selection for pc players*/
				b.addActionListener(e -> squareClicked(p));

				b.setMargin(buttonMargin);
				/* our chess pieces are 64x64 px in size, so we'll
				 'fill this in' using a transparent icon.. */
				ImageIcon icon = new ImageIcon(
						new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0)) {
					b.setBackground(Color.WHITE);
				} else {
					b.setBackground(Color.BLACK);
				}
				chessboardSquares[y][x] = b;
			}
		}
	}

	private synchronized void squareClicked(Point p) {
		if(!controller.isAIOn(controller.playerToMove())) {
			if(getMarkedSquare() == null && ((pieceAt(p) == 0 && controller.playerToMove() == WHITE) || (pieceAt(p) == 1 && controller.playerToMove() == BLACK))) {
				markSquare(p);
			} else if (getMarkedSquare() != null) {
				ChessMove move = new ChessMove(getIndexAtPosition(getMarkedSquare()), getIndexAtPosition(p));
				controller.makeMove(move);
				unmarkSquare();
			}
		}
	}

	private synchronized int pieceAt(Point p) {
		int index = getIndexAtPosition(p);
		if(controller.getCurrentPosition().getWhitePieces().get(index)) {
			return 0;
		} else if(controller.getCurrentPosition().getBlackPieces().get(index)) {
			return 1;
		}
		return -1;
	}

	private void markSquare(Point p) {
		chessboardSquares[p.y][p.x].setBackground(Color.yellow);
		markedSquare = p;
	}

	private void unmarkSquare() {
		if ((markedSquare.y % 2 == 1 && markedSquare.x % 2 == 1)
				|| (markedSquare.y % 2 == 0 && markedSquare.x % 2 == 0)) {
			chessboardSquares[markedSquare.y][markedSquare.x].setBackground(Color.WHITE);
		} else {
			chessboardSquares[markedSquare.y][markedSquare.x].setBackground(Color.BLACK);
		}
		markedSquare = null;
	}

	private Point getMarkedSquare() {
		return markedSquare;
	}

	private void fillChessboard() {
		//Fill empty square in top left corner
		add(new JLabel(""));
		// fill the top row with letters A-H
		for (int x = 0; x < 8; x++) {
			add(new JLabel(COLS.substring(x, x + 1),
					SwingConstants.CENTER));
		}
		// fill the black non-pawn piece row
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				switch (x) {
				case 0: //Number 1-8
					add(new JLabel("" + (9-(y + 1)),
							SwingConstants.CENTER));
				default: //Colored square
					add(chessboardSquares[y][x]);
				}
			}
		}
	}

	private void createImages() {
		/*Create the chess piece images*/
		try {
			URL url = new URL("http://i.stack.imgur.com/memI0.png");
			BufferedImage bi = ImageIO.read(url);
			for (int y = 0; y < 2; y++) {
				for (int x = 0; x < 6; x++) {
					chessPieceImages[y][x] = bi.getSubimage(
							x * 64, y * 64, 64, 64);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}


	/**
	 * Initializes the icons of the initial chess board piece places
	 */
	void updateBoard(ChessPosition model) {
		BitSet whiteRooks = model.getRooks(WHITE);
		BitSet blackRooks = model.getRooks(BLACK);
		BitSet whiteKnights = model.getKnights(WHITE);
		BitSet blackKnights = model.getKnights(BLACK);
		BitSet whiteBishops = model.getBishops(WHITE);
		BitSet blackBishops = model.getBishops(BLACK);
		BitSet whiteQueens = model.getQueens(WHITE);
		BitSet blackQueens = model.getQueens(BLACK);
		BitSet whiteKing = model.getKing(WHITE);
		BitSet blackKing = model.getKing(BLACK);
		BitSet whitePawns = model.getPawns(WHITE);
		BitSet blackPawns = model.getPawns(BLACK);
		BitSet allPieces = model.getAllPieces();

		for (int location = allPieces.nextSetBit(0); location >= 0; location = allPieces
				.nextSetBit(location + 1)) {
			Point p = getPositionAtIndex(location);
			if(whiteRooks.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[1][2]));
			} else if(blackRooks.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[0][2]));
			} else if(whiteBishops.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[1][4]));
			} else if(blackBishops.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[0][4]));
			} else if(whiteQueens.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[1][0]));
			} else if(blackQueens.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[0][0]));
			} else if(whiteKing.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[1][1]));
			} else if(blackKing.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[0][1]));
			} else if(whitePawns.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[1][5]));
			} else if(blackPawns.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[0][5]));
			} else if(whiteKnights.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[1][3]));
			} else if(blackKnights.get(location)) {
				chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
						chessPieceImages[0][3]));
			}
		}
		for (int location = allPieces.nextClearBit(0); location != 64; location = allPieces
				.nextClearBit(location + 1)) {
			Point p = getPositionAtIndex(location);
			chessboardSquares[p.y][p.x].setIcon(new ImageIcon(
					new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
		}

	}

	void setClickingActivated(boolean activated) {
		for (int y = 0; y < chessboardSquares.length; y++) {
			for (int x = 0; x < chessboardSquares[y].length; x++) {
				chessboardSquares[x][y].setEnabled(activated);
			}
		}
	}
}
