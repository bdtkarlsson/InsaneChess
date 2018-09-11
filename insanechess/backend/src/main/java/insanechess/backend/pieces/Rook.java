package insanechess.backend.pieces;

import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;

import java.util.BitSet;

import static insanechess.backend.ChessConstants.*;
import static insanechess.backend.constants.Files.ALL_FILES;
import static insanechess.backend.constants.Ranks.ALL_RANKS;

public final class Rook {

	private Rook() {
	}
	
	public static void calculateLegalMoves(Player player, InsaneChessPosition model) {
		BitSet rooks = model.getRooks(player);
		BitSet alliedPieces = model.getAlliedPieces(player);
		BitSet enemyPieces = model.getOpponentPieces(player);

		for (int possibleRookLocation = rooks.nextSetBit(0); possibleRookLocation >= 0; possibleRookLocation = rooks
		        .nextSetBit(possibleRookLocation + 1)) {
			final BitSet vertical = ALL_FILES.get(possibleRookLocation);
			final BitSet horizontal = ALL_RANKS.get(possibleRookLocation);
			// up
			int candidateLocation = possibleRookLocation - 8;
			int endPos = vertical.nextSetBit(0);
			while (candidateLocation >= endPos) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				model.addLegalLocation(player, candidateLocation);
				model.addLegalMove(player, new ChessMove(possibleRookLocation,
				        candidateLocation));
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation -= 8;
			}

			// down
			candidateLocation = possibleRookLocation + 8;
			endPos = vertical.length();
			while (endPos >= candidateLocation) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				model.addLegalLocation(player, candidateLocation);
				model.addLegalMove(player,new ChessMove(possibleRookLocation,
				        candidateLocation));
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation += 8;
			}

			// left
			candidateLocation = possibleRookLocation - 1;
			endPos = horizontal.nextSetBit(0);
			while (candidateLocation >= endPos) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				model.addLegalLocation(player, candidateLocation);
				model.addLegalMove(player,new ChessMove(possibleRookLocation,
				        candidateLocation));
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation--;
			}

			// right
			candidateLocation = possibleRookLocation + 1;
			endPos = horizontal.length() - 1;
			while (candidateLocation <= endPos) {
				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				model.addLegalLocation(player, candidateLocation);
				model.addLegalMove(player,new ChessMove(possibleRookLocation,
				        candidateLocation));
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation++;
			}
		}
	}
}
