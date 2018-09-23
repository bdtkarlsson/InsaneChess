package insanechess.backend.pieces;

import insanechess.backend.ChessMove;
import insanechess.backend.ChessPosition;
import insanechess.backend.constants.Player;

import java.util.BitSet;

import static insanechess.backend.constants.Files.*;
import static insanechess.backend.constants.Validations.isValidTile;

public final class Knight{

	private Knight() {
	}

	public static void calculateLegalMoves(Player player, ChessPosition model) {
		BitSet knights = model.getKnights(player);
		BitSet alliedPieces = model.getAlliedPieces(player);

		for (int knightLocation = knights.nextSetBit(0); knightLocation >= 0; knightLocation = knights
				.nextSetBit(knightLocation + 1)) {

			int candidateLocation = knightLocation - 6;
			if (!FILE_G.get(knightLocation) && !FILE_H.get(knightLocation) && isValidTargetTile(alliedPieces, candidateLocation)) {
				addKnightMove(player, model, knightLocation, candidateLocation);
			}
			candidateLocation = knightLocation - 10;
			if (!FILE_A.get(knightLocation) && !FILE_B.get(knightLocation) && isValidTargetTile(alliedPieces, candidateLocation)) {
				addKnightMove(player, model, knightLocation, candidateLocation);
			}
			 candidateLocation = knightLocation - 15;
			if (!FILE_H.get(knightLocation) && isValidTargetTile(alliedPieces, candidateLocation)) {
				addKnightMove(player, model, knightLocation, candidateLocation);
			}
			 candidateLocation = knightLocation - 17;
			if (!FILE_A.get(knightLocation) && isValidTargetTile(alliedPieces, candidateLocation)) {
				addKnightMove(player, model, knightLocation, candidateLocation);

			}
			candidateLocation = knightLocation + 6;
			if (!FILE_A.get(knightLocation) && !FILE_B.get(knightLocation) && isValidTargetTile(alliedPieces, candidateLocation)) {
				addKnightMove(player, model, knightLocation, candidateLocation);

			}
			candidateLocation = knightLocation + 10;
			if (!FILE_G.get(knightLocation) && !FILE_H.get(knightLocation) && isValidTargetTile(alliedPieces, candidateLocation)) {
				addKnightMove(player, model, knightLocation, candidateLocation);

			}
			candidateLocation = knightLocation + 15;
			if (!FILE_A.get(knightLocation) && isValidTargetTile(alliedPieces, candidateLocation)) {
				addKnightMove(player, model, knightLocation, candidateLocation);

			}
			candidateLocation = knightLocation + 17;
			if (!FILE_H.get(knightLocation) && isValidTargetTile(alliedPieces, candidateLocation)) {
				addKnightMove(player, model, knightLocation, candidateLocation);
			}
		}
	}

	private static boolean isValidTargetTile(BitSet alliedPieces, int candidateLocation) {
		return isValidTile(candidateLocation) && !alliedPieces.get(candidateLocation);
	}

	private static void addKnightMove(Player player, ChessPosition model, int knightLocation, int candidateLocation) {
		model.addLegalMove(player, new ChessMove(knightLocation,
				candidateLocation));
		model.addLegalLocation(player, candidateLocation);
	}
}