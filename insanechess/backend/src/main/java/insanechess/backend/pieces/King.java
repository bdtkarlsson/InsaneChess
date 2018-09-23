package insanechess.backend.pieces;

import insanechess.backend.constants.FullEmptySet;
import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;
import insanechess.backend.constants.Player;

import java.util.BitSet;

import static insanechess.backend.constants.Files.FILE_A;
import static insanechess.backend.constants.Files.FILE_H;
import static insanechess.backend.constants.Player.BLACK;
import static insanechess.backend.constants.Player.WHITE;
import static insanechess.backend.constants.Validations.isValidTile;

public final class King {

	private King() {
	}

	public static void calculateLegalMoves(Player player, ChessPosition model) {
		BitSet king = model.getKing(player);
		int kingLocation = king.nextSetBit(0);
		BitSet alliedPieces = model.getAlliedPieces(player);
		BitSet allPieces = model.getAllPieces();

		if(kingLocation != -1) {

			if(isWhiteShortCastling(player, model, allPieces)) {
				model.addLegalMove(player, new ChessMove(kingLocation, 63));
				model.addLegalLocation(player, 62);
				model.addLegalLocation(player, 61);
			} 
			
			if (isWhiteLongCastling(player, model, allPieces)) {
				model.addLegalMove(player, new ChessMove(kingLocation, 56));
				model.addLegalLocation(player, 58);
				model.addLegalLocation(player, 59);
			} 
			
			if (isBlackShortCastling(player, model, allPieces)) {
				model.addLegalMove(player, new ChessMove(kingLocation, 7));
				model.addLegalLocation(player, 5);
				model.addLegalLocation(player, 6);
			} 
			
			if (isBlackLongCastling(player, model, allPieces)) {
				model.addLegalMove(player, new ChessMove(kingLocation, 0));
				model.addLegalLocation(player, 2);
				model.addLegalLocation(player, 3);
			}

			/*Regular king moves*/

			/*Left*/
			int possibleTargetLocation = kingLocation - 1;
			if (isValidTargetTile(alliedPieces, possibleTargetLocation) && !FILE_A.get(kingLocation)) {
				addRegularKingMove(player, model, kingLocation, possibleTargetLocation);
			}

			/*Right*/
			possibleTargetLocation = kingLocation + 1;
			if (isValidTargetTile(alliedPieces, possibleTargetLocation) && !FILE_H.get(kingLocation)) {
				addRegularKingMove(player, model, kingLocation, possibleTargetLocation);
			}

			/*Down*/
			possibleTargetLocation = kingLocation + 8;
			if (isValidTargetTile(alliedPieces, possibleTargetLocation) && FullEmptySet.FULL_SET.get(possibleTargetLocation)) {
				addRegularKingMove(player, model, kingLocation, possibleTargetLocation);
			}

			/*Up*/
			possibleTargetLocation = kingLocation - 8;
			if (isValidTargetTile(alliedPieces, possibleTargetLocation) && FullEmptySet.FULL_SET.get(possibleTargetLocation)) {
				addRegularKingMove(player, model, kingLocation, possibleTargetLocation);
			}

			/*Up and right*/
			possibleTargetLocation = kingLocation - 7;
			if (isValidTargetTile(alliedPieces, possibleTargetLocation) && !FILE_H.get(kingLocation)) {
				addRegularKingMove(player, model, kingLocation, possibleTargetLocation);
			}

			/*Down and right*/
			possibleTargetLocation = kingLocation + 9;
			if (isValidTargetTile(alliedPieces, possibleTargetLocation) && !FILE_H.get(kingLocation)) {
				addRegularKingMove(player, model, kingLocation, possibleTargetLocation);
			}

			/*Up and left*/
			possibleTargetLocation = kingLocation - 9;
			if (isValidTargetTile(alliedPieces, possibleTargetLocation) && !FILE_A.get(kingLocation)) {
				addRegularKingMove(player, model, kingLocation, possibleTargetLocation);
			}

			/*Down and left*/
			possibleTargetLocation = kingLocation + 7;
			if (isValidTargetTile(alliedPieces, possibleTargetLocation) && !FILE_A.get(kingLocation)) {
				addRegularKingMove(player, model, kingLocation, possibleTargetLocation);
			}
		}
	}

	private static boolean isValidTargetTile(BitSet alliedPieces, int possibleTargetLocation) {
		return isValidTile(possibleTargetLocation) && !alliedPieces.get(possibleTargetLocation);
	}

	private static void addRegularKingMove(Player player, ChessPosition model, int kingLocation, int possibleTargetLocation) {
		model.addLegalMove(player, new ChessMove(kingLocation, possibleTargetLocation));
		model.addLegalLocation(player, possibleTargetLocation);
	}

	private static boolean isBlackLongCastling(Player player, ChessPosition model, BitSet allPieces) {
		return player == BLACK && model.isBlackLongCastlingLegal() && !allPieces.get(1) && !allPieces.get(2) && !allPieces.get(3);
	}

	private static boolean isBlackShortCastling(Player player, ChessPosition model, BitSet allPieces) {
		return player == BLACK && model.isBlackShortCastlingLegal() && !allPieces.get(5) && !allPieces.get(6);
	}

	private static boolean isWhiteLongCastling(Player player, ChessPosition model, BitSet allPieces) {
		return player == WHITE && model.isWhiteLongCastlingLegal() && !allPieces.get(57) && !allPieces.get(58) && !allPieces.get(59);
	}

	private static boolean isWhiteShortCastling(Player player, ChessPosition model, BitSet allPieces) {
		return player == WHITE && model.isWhiteShortCastlingLegal() && !allPieces.get(61) && !allPieces.get(62);
	}
}