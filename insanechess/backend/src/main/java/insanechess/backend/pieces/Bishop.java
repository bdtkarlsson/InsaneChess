package insanechess.backend.pieces;

import insanechess.backend.constants.FullEmptySet;
import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;
import insanechess.backend.constants.Player;

import java.util.BitSet;

import static insanechess.backend.constants.Diagonals.ALL_LEFT_DIAGONALS;
import static insanechess.backend.constants.Diagonals.ALL_RIGHT_DIAGONALS;

public final class Bishop{

	public static void calculateLegalMoves(Player player, InsaneChessPosition model) {
		BitSet bishops = model.getBishops(player);
		BitSet alliedPieces = model.getAlliedPieces(player);
		BitSet enemyPieces = model.getOpponentPieces(player);

		//Loop through all bishops of the correct color
		for (int bishopLocation = bishops.nextSetBit(0); bishopLocation >= 0; bishopLocation = bishops
		        .nextSetBit(bishopLocation + 1)) {
			final BitSet rightDiag = ALL_RIGHT_DIAGONALS.get(bishopLocation);
			final BitSet leftDiag = ALL_LEFT_DIAGONALS.get(bishopLocation);
			
			// up and to the right
			int candidateLocation = bishopLocation - 7;
			int endPos = rightDiag.nextSetBit(0);
			while (candidateLocation >= endPos) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				model.addLegalLocation(player, candidateLocation);
				model.addLegalMove(player, new ChessMove(bishopLocation,
				        candidateLocation));
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation -= 7;
			}

			// down and to the left
			candidateLocation = bishopLocation + 7;
			endPos = rightDiag.length() - 1;
			while (endPos >= candidateLocation) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				model.addLegalLocation(player, candidateLocation);
				model.addLegalMove(player, new ChessMove(bishopLocation,
				        candidateLocation));
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation += 7;
			}

			// up and to the left
			candidateLocation = bishopLocation - 9;
			endPos = leftDiag.nextSetBit(0);
			while (candidateLocation >= endPos) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				model.addLegalLocation(player, candidateLocation);
				model.addLegalMove(player, new ChessMove(bishopLocation,
				        candidateLocation));
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation -= 9;
			}

			// down and to the right
			candidateLocation = bishopLocation + 9;
			endPos = leftDiag.length() - 1;
			while (candidateLocation <= endPos) {
				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				model.addLegalLocation(player, candidateLocation);
				model.addLegalMove(player, new ChessMove(bishopLocation,
				        candidateLocation));
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation += 9;
			}
		}
	}
}