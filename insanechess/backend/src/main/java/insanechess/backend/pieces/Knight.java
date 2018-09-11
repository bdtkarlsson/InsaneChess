package insanechess.backend.pieces;

import insanechess.backend.ChessConstants;
import insanechess.backend.ChessConstants.Player;
import insanechess.backend.ChessMove;
import insanechess.backend.InsaneChessPosition;

import java.util.BitSet;

import static insanechess.backend.constants.Files.*;

public final class Knight{

	private Knight() {
	}

	public static void calculateLegalMoves(Player player, InsaneChessPosition model) {
		BitSet knights = model.getKnights(player);
		BitSet alliedPieces = model.getAlliedPieces(player);

		for (int knightLocation = knights.nextSetBit(0); knightLocation >= 0; knightLocation = knights
				.nextSetBit(knightLocation + 1)) {

			int candidateLocation = knightLocation - 6;
			if (!FILE_G.get(knightLocation) && !FILE_H
					.get(knightLocation) && ChessConstants.isValidTile(candidateLocation) && !alliedPieces.get(candidateLocation)) {
					model.addLegalMove(player, new ChessMove(knightLocation,
							candidateLocation));
					model.addLegalLocation(player, candidateLocation);
				
			}
			candidateLocation = knightLocation - 10;
			if (!FILE_A.get(knightLocation) && !FILE_B
					.get(knightLocation) && ChessConstants.isValidTile(candidateLocation) && !alliedPieces.get(candidateLocation)) {
					model.addLegalMove(player, new ChessMove(knightLocation,
							candidateLocation));
					model.addLegalLocation(player, candidateLocation);

			}
			 candidateLocation = knightLocation - 15;
			if (!FILE_H.get(knightLocation) && ChessConstants.isValidTile(candidateLocation)
					&& !alliedPieces.get(candidateLocation)) {
					model.addLegalMove(player, new ChessMove(knightLocation,
							candidateLocation));
					model.addLegalLocation(player, candidateLocation);


				
			}
			 candidateLocation = knightLocation - 17;
			if (!FILE_A.get(knightLocation) && ChessConstants.isValidTile(candidateLocation)
					&& !alliedPieces.get(candidateLocation)) {
					model.addLegalMove(player, new ChessMove(knightLocation,
							candidateLocation));
					model.addLegalLocation(player, candidateLocation);

			}
			candidateLocation = knightLocation + 6;
			if (!FILE_A.get(knightLocation) && !FILE_B
					.get(knightLocation) && ChessConstants.isValidTile(candidateLocation)
					&& !alliedPieces.get(candidateLocation)) {
					model.addLegalMove(player, new ChessMove(knightLocation,
							candidateLocation));
					model.addLegalLocation(player, candidateLocation);

			}
			candidateLocation = knightLocation + 10;
			if (!FILE_G.get(knightLocation) && !FILE_H
					.get(knightLocation) && ChessConstants.isValidTile(candidateLocation)
							&& !alliedPieces.get(candidateLocation)) {
					model.addLegalMove(player, new ChessMove(knightLocation,
							candidateLocation));
					model.addLegalLocation(player, candidateLocation);

			}
			candidateLocation = knightLocation + 15;
			if (!FILE_A.get(knightLocation) && ChessConstants.isValidTile(candidateLocation)
					&& !alliedPieces.get(candidateLocation)) {
					model.addLegalMove(player, new ChessMove(knightLocation,
							candidateLocation));
					model.addLegalLocation(player, candidateLocation);

			}
			candidateLocation = knightLocation + 17;
			if (!FILE_H.get(knightLocation) && ChessConstants.isValidTile(candidateLocation)
					&& !alliedPieces.get(candidateLocation)) {
					model.addLegalMove(player, new ChessMove(knightLocation,
							candidateLocation));
					model.addLegalLocation(player, candidateLocation);

			}
		}
	}
}