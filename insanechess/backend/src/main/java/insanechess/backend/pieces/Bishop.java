package insanechess.backend.pieces;

import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;
import insanechess.backend.constants.Player;

import java.util.BitSet;

import static insanechess.backend.constants.Diagonals.ALL_LEFT_DIAGONALS;
import static insanechess.backend.constants.Diagonals.ALL_RIGHT_DIAGONALS;

public final class Bishop{

	public static void calculateLegalMoves(Player player, ChessPosition model) {
		BitSet bishops = model.getBishops(player);
		BitSet alliedPieces = model.getAlliedPieces(player);
		BitSet enemyPieces = model.getOpponentPieces(player);

		for (int bishopLocation = bishops.nextSetBit(0); bishopLocation >= 0; bishopLocation = nextBishop(bishops, bishopLocation)) {
			final BitSet rightDiag = ALL_RIGHT_DIAGONALS.get(bishopLocation);
			final BitSet leftDiag = ALL_LEFT_DIAGONALS.get(bishopLocation);
			
			// up and to the right
			int candidateLocation = bishopLocation - 7;
			int endPos = rightDiag.nextSetBit(0);
			while (candidateLocation >= endPos) {

				if (alliedPieces.get(candidateLocation)) {
					break;
				}
				addBishopMove(player, model, bishopLocation, candidateLocation);
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
				addBishopMove(player, model, bishopLocation, candidateLocation);
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
				addBishopMove(player, model, bishopLocation, candidateLocation);
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
				addBishopMove(player, model, bishopLocation, candidateLocation);
				if (enemyPieces.get(candidateLocation)) {
					break;
				}
				candidateLocation += 9;
			}
		}
	}

	private static void addBishopMove(Player player, ChessPosition model, int bishopLocation, int candidateLocation) {
		model.addLegalLocation(player, candidateLocation);
		model.addLegalMove(player, new ChessMove(bishopLocation, candidateLocation));
	}

	private static int nextBishop(BitSet bishops, int bishopLocation) {
		return bishops.nextSetBit(bishopLocation + 1);
	}
}