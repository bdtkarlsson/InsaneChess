package insanechess.backend.pieces;

import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;
import insanechess.backend.constants.Player;

import java.util.BitSet;

import static insanechess.backend.constants.Files.FILE_A;
import static insanechess.backend.constants.Files.FILE_H;
import static insanechess.backend.constants.Player.WHITE;
import static insanechess.backend.constants.Ranks.RANK_2;
import static insanechess.backend.constants.Ranks.RANK_7;
import static insanechess.backend.constants.Validations.isValidTile;

public final class Pawn{

	private Pawn() {
	}
	public static void calculateLegalMoves(Player player, ChessPosition model) {
		BitSet allPieces = model.getAllPieces();
		
		BitSet enemyPieces = model.getOpponentPieces(player);
		/*Consider enemy en passants as well*/
		enemyPieces.or(model.getEnPassants(player));
		BitSet pawns = model.getPawns(player);
			
		int pawnAdvance, pawnJump, pawnAttackLeft, pawnAttackRight;
		BitSet beginRank;
		if(player == WHITE) {
			pawnAdvance = -8;
			pawnJump = -16;
			pawnAttackLeft = -9;
			pawnAttackRight = -7;
			beginRank = RANK_2;
		} else {
			pawnAdvance = 8;
			pawnJump = 16;
			pawnAttackLeft = 7;
			pawnAttackRight = 9;
			beginRank = RANK_7;
		}
		
		for (int pawnLocation = pawns.nextSetBit(0); pawnLocation >= 0; pawnLocation = nextPawn(pawns, pawnLocation)) {

			int candidateLocation = pawnLocation + pawnAdvance;
			if (isValidTile(candidateLocation) && !allPieces.get(candidateLocation)) {
				addPawnMove(player, model, pawnLocation, candidateLocation);
			}

			candidateLocation = pawnLocation + pawnJump;
			if (isValidTile(candidateLocation) && !allPieces.get(candidateLocation) && !allPieces.get(pawnLocation + pawnAdvance) &&
					beginRank.get(pawnLocation)) {
				addPawnMove(player, model, pawnLocation, candidateLocation);
			}
			
			candidateLocation = pawnLocation + pawnAttackLeft;
			if (isValidTile(candidateLocation) && enemyPieces.get(candidateLocation) && !FILE_A.get(pawnLocation)) {
				addPawnMove(player, model, pawnLocation, candidateLocation);
			}	
			
			candidateLocation = pawnLocation + pawnAttackRight;
			if (isValidTile(candidateLocation) && enemyPieces.get(candidateLocation) && !FILE_H.get(pawnLocation)) {
				addPawnMove(player, model, pawnLocation, candidateLocation);
			}
		}
	}

	private static void addPawnMove(Player player, ChessPosition model, int pawnLocation, int candidateLocation) {
		model.addLegalMove(player, new ChessMove(pawnLocation, candidateLocation));
		model.addLegalLocation(player, candidateLocation);
	}

	private static int nextPawn(BitSet pawns, int pawnLocation) {
		return pawns.nextSetBit(pawnLocation + 1);
	}
}