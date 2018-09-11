package insanechess.backend.pieces;

import insanechess.backend.InsaneChessPosition;
import insanechess.backend.constants.Player;

import java.util.BitSet;

public final class Queen{

	private Queen() {
	}

	public static void calculateLegalMoves(Player player, InsaneChessPosition model) {
		BitSet queens = model.getQueens(player);

		for (int queenLocation = queens.nextSetBit(0); queenLocation >= 0; queenLocation = queens
		        .nextSetBit(queenLocation + 1)) {
			
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
}