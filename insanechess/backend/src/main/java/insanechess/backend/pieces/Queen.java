package insanechess.backend.pieces;

import insanechess.backend.ChessPosition;
import insanechess.backend.constants.Player;

import java.util.BitSet;

public final class Queen{

	private Queen() {
	}

	public static void calculateLegalMoves(Player player, ChessPosition model) {
		BitSet queens = model.getQueens(player);

		for (int queenLocation = queens.nextSetBit(0); queenLocation >= 0; queenLocation = nextQueen(queens, queenLocation)) {
			
			BitSet queen = new BitSet();
			queen.set(queenLocation);
			BitSet savedBishops = model.getBishops(player);
			BitSet savedRooks = model.getRooks(player);
						
			model.setBishops(player, queen);
			Bishop.calculateLegalMoves(player, model);
			model.setBishops(player, savedBishops);
					
			model.setRooks(player, queen);
			Rook.calculateLegalMoves(player, model);
			model.setRooks(player, savedRooks);
		}
	}

	private static int nextQueen(BitSet queens, int queenLocation) {
		return queens.nextSetBit(queenLocation + 1);
	}
}