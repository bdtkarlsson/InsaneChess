package insanechess.backend.pieces;

import insanechess.backend.ChessConstants;
import insanechess.backend.ChessConstants.Player;
import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;

import java.util.BitSet;

import static insanechess.backend.ChessConstants.Player.BLACK;
import static insanechess.backend.ChessConstants.Player.WHITE;
import static insanechess.backend.constants.Files.FILE_A;
import static insanechess.backend.constants.Files.FILE_H;

public final class King{

	private King() {
	}

	public static void calculateLegalMoves(Player player, InsaneChessPosition model) {
		BitSet king = model.getKing(player);
		int kingLocation = king.nextSetBit(0);
		BitSet alliedPieces = model.getAlliedPieces(player);
		BitSet allPieces = model.getAllPieces();

		if(kingLocation != -1) {

			/*Castling*/
			if(player == WHITE && model.isWhiteShortCastlingLegal() && !allPieces.get(61) && !allPieces.get(62)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						63));
				model.addLegalLocation(player, 62);
				model.addLegalLocation(player, 61);
			} 
			
			if (player == WHITE && model.isWhiteLongCastlingLegal() && !allPieces.get(57) && !allPieces.get(58) &&
					!allPieces.get(59)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						56));
				model.addLegalLocation(player, 58);
				model.addLegalLocation(player, 59);
			} 
			
			if (player == BLACK && model.isBlackShortCastlingLegal() && !allPieces.get(5) && !allPieces.get(6)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						7));
				model.addLegalLocation(player, 5);
				model.addLegalLocation(player, 6);
			} 
			
			if (player == BLACK && model.isBlackLongCastlingLegal() && !allPieces.get(1) && !allPieces.get(2) &&
					!allPieces.get(3)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						0));
				model.addLegalLocation(player, 2);
				model.addLegalLocation(player, 3);
			}

			/*Regular king moves*/

			/*Left*/
			int moveLocation = kingLocation - 1;
			if (alliedPieces.get(kingLocation) && !FILE_A.get(kingLocation)
					&& ChessConstants.isValidTile(moveLocation) && !alliedPieces.get(moveLocation)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						moveLocation));
				model.addLegalLocation(player, moveLocation);


			}
			/*Right*/
			moveLocation = kingLocation + 1;
			if (alliedPieces.get(kingLocation) && !FILE_H.get(kingLocation)
					&& ChessConstants.isValidTile(moveLocation) && !alliedPieces.get(moveLocation)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						moveLocation));
				model.addLegalLocation(player, moveLocation);


			}
			/*Down*/
			moveLocation = kingLocation + 8;
			if (ChessConstants.isValidTile(moveLocation) && alliedPieces.get(kingLocation)
					&& (ChessConstants.FULL_SET.get(moveLocation)) && !alliedPieces.get(moveLocation)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						moveLocation));
				model.addLegalLocation(player, moveLocation);


			}

			/*Up*/
			moveLocation = kingLocation - 8;
			if (ChessConstants.isValidTile(moveLocation) && alliedPieces.get(kingLocation)
					&& (ChessConstants.FULL_SET.get(moveLocation)) && !alliedPieces.get(moveLocation)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						moveLocation));
				model.addLegalLocation(player, moveLocation);


			}

			/*Up and right*/
			moveLocation = kingLocation - 7;
			if (alliedPieces.get(kingLocation) && !FILE_H.get(kingLocation)
					&& ChessConstants.isValidTile(moveLocation) && !alliedPieces.get(moveLocation)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						moveLocation));
				model.addLegalLocation(player, moveLocation);


			}

			/*Down and right*/
			moveLocation = kingLocation + 9;
			if (alliedPieces.get(kingLocation) && !FILE_H.get(kingLocation)
					&& ChessConstants.isValidTile(moveLocation) && !alliedPieces.get(moveLocation)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						moveLocation));
				model.addLegalLocation(player, moveLocation);


			}

			/*Up and left*/
			moveLocation = kingLocation - 9;
			if (alliedPieces.get(kingLocation) && !FILE_A.get(kingLocation)
					&& ChessConstants.isValidTile(moveLocation) && !alliedPieces.get(moveLocation)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						moveLocation));
				model.addLegalLocation(player, moveLocation);


			}

			/*Down and left*/
			moveLocation = kingLocation + 7;
			if (alliedPieces.get(kingLocation) && !FILE_A.get(kingLocation)
					&& ChessConstants.isValidTile(moveLocation) && !alliedPieces.get(moveLocation)) {
				model.addLegalMove(player, new ChessMove(kingLocation,
						moveLocation));
				model.addLegalLocation(player, moveLocation);


			}	

		}
	}
}