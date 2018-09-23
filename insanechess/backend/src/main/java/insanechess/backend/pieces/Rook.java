package insanechess.backend.pieces;

import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;
import insanechess.backend.constants.Player;

import java.util.BitSet;

import static insanechess.backend.constants.Files.ALL_FILES;
import static insanechess.backend.constants.Ranks.ALL_RANKS;

public final class Rook {

	private Rook() {
	}
	
	public static void calculateLegalMoves(Player player, ChessPosition model) {
		BitSet rooks = model.getRooks(player);
		BitSet alliedPieces = model.getAlliedPieces(player);
		BitSet enemyPieces = model.getOpponentPieces(player);

		for (int rookLocation = rooks.nextSetBit(0); rookLocation >= 0; rookLocation = nextRook(rooks, rookLocation)) {
			final BitSet vertical = ALL_FILES.get(rookLocation);
			final BitSet horizontal = ALL_RANKS.get(rookLocation);
			// up
			int candidateLocation = rookLocation - 8;
			int endPos = vertical.nextSetBit(0);
			while (candidateLocation >= endPos) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				addRookMove(player, model, rookLocation, candidateLocation);
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation -= 8;
			}

			// down
			candidateLocation = rookLocation + 8;
			endPos = vertical.length();
			while (endPos >= candidateLocation) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				addRookMove(player, model, rookLocation, candidateLocation);
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation += 8;
			}

			// left
			candidateLocation = rookLocation - 1;
			endPos = horizontal.nextSetBit(0);
			while (candidateLocation >= endPos) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				addRookMove(player, model, rookLocation, candidateLocation);
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation--;
			}

			// right
			candidateLocation = rookLocation + 1;
			endPos = horizontal.length() - 1;
			while (candidateLocation <= endPos) {
				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				addRookMove(player, model, rookLocation, candidateLocation);
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation++;
			}
		}
	}

	private static void addRookMove(Player player, ChessPosition model, int rookLocation, int candidateLocation) {
		model.addLegalLocation(player, candidateLocation);
		model.addLegalMove(player, new ChessMove(rookLocation, candidateLocation));
	}

	private static int nextRook(BitSet rooks, int possibleRookLocation) {
		return rooks.nextSetBit(possibleRookLocation + 1);
	}
}
