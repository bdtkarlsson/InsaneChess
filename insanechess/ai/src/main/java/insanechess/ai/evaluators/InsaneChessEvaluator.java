package insanechess.ai.evaluators;

import insanechess.backend.ChessPosition;

import java.util.BitSet;

import static insanechess.backend.constants.Files.*;
import static insanechess.backend.constants.Player.BLACK;
import static insanechess.backend.constants.Player.WHITE;

/**
 * The chess model is evaluated with several different measurements. The number of legal moves, piece values,
 * special pawn values and the position of each piece are considered.
 * @author Daniel
 *
 */
public class InsaneChessEvaluator implements ChessEvaluator {

	/*Position value tables. Each piece has its own table with predetermined values for 
	 * the different positions*/
	
	private static final double[] WHITEPAWNTABLE = {
		0,  0,  0,  0,  0,  0,  0,  0,
		50, 50, 50, 50, 50, 50, 50, 50,
		10, 10, 20, 30, 30, 20, 10, 10,
		5,  5, 10, 25, 25, 10,  5,  5,
		0,  0,  0, 20, 20,  0,  0,  0,
		5, -5,-10,  0,  0,-10, -5,  5,
		5, 10, 10,-20,-20, 10, 10,  5,
		0,  0,  0,  0,  0,  0,  0,  0
	};

	private static final double[] BLACKPAWNTABLE = {
		0,  0,  0,  0,  0,  0,  0,  0,
		5, 10, 10,-20,-20, 10, 10,  5,
		5, -5,-10,  0,  0,-10, -5,  5,
		0,  0,  0, 20, 20,  0,  0,  0,
		5,  5, 10, 25, 25, 10,  5,  5,
		10, 10, 20, 30, 30, 20, 10, 10,
		50, 50, 50, 50, 50, 50, 50, 50,
		0,  0,  0,  0,  0,  0,  0,  0
	};

	private static final double[] WHITEKNIGHTTABLE = {
		-50,-40,-30,-30,-30,-30,-40,-50,
		-40,-20,  0,  0,  0,  0,-20,-40,
		-30,  0, 10, 15, 15, 10,  0,-30,
		-30,  5, 15, 20, 20, 15,  5,-30,
		-30,  0, 15, 20, 20, 15,  0,-30,
		-30,  5, 10, 15, 15, 10,  5,-30,
		-40,-20,  0,  5,  5,  0,-20,-40,
		-50,-40,-30,-30,-30,-30,-40,-50
	};

	private static final double[] BLACKKNIGHTTABLE = {
		-50,-40,-30,-30,-30,-30,-40,-50,
		-40,-20,  0,  5,  5,  0,-20,-40,
		-30,  5, 10, 15, 15, 10,  5,-30,
		-30,  0, 15, 20, 20, 15,  0,-30,
		-30,  5, 15, 20, 20, 15,  5,-30,
		-30,  0, 10, 15, 15, 10,  0,-30,
		-40,-20,  0,  0,  0,  0,-20,-40,
		-50,-40,-30,-30,-30,-30,-40,-50
	};


	private static final double[] WHITEBISHOPTABLE = {
		-20,-10,-10,-10,-10,-10,-10,-20,
		-10,  0,  0,  0,  0,  0,  0,-10,
		-10,  0,  5, 10, 10,  5,  0,-10,
		-10,  5,  5, 10, 10,  5,  5,-10,
		-10,  0, 10, 10, 10, 10,  0,-10,
		-10, 10, 10, 10, 10, 10, 10,-10,
		-10,  5,  0,  0,  0,  0,  5,-10,
		-20,-10,-10,-10,-10,-10,-10,-20
	};

	private static final double[] BLACKBISHOPTABLE = {

		-20,-10,-10,-10,-10,-10,-10,-20,
		-10,  5,  0,  0,  0,  0,  5,-10,
		-10, 10, 10, 10, 10, 10, 10,-10,
		-10,  0, 10, 10, 10, 10,  0,-10,
		-10,  5,  5, 10, 10,  5,  5,-10,
		-10,  0,  5, 10, 10,  5,  0,-10,
		-10,  0,  0,  0,  0,  0,  0,-10,
		-20,-10,-10,-10,-10,-10,-10,-20
	};

	private static final double[] WHITEROOKTABLE = {
		 0,  0,  0,  0,  0,  0,  0,  0,
		 5, 10, 10, 10, 10, 10, 10,  5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		 0,  0,  0,  5,  5,  0,  0,  0
	};

	private static final double[] BLACKROOKTABLE = {
		 0,  0,  0,  5,  5,  0,  0,  0,
		-5,  0,  0,  0,  0,  0,  0, -5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		-5,  0,  0,  0,  0,  0,  0, -5,
		 5, 10, 10, 10, 10, 10, 10,  5,	
		 0,  0,  0,  0,  0,  0,  0,  0
	};

	private static final double[] WHITEQUEENTABLE = {
		-20,-10,-10, -5, -5,-10,-10,-20,
		-10,  0,  0,  0,  0,  0,  0,-10,
		-10,  0,  5,  5,  5,  5,  0,-10,
		 -5,  0,  5,  5,  5,  5,  0, -5,
		  0,  0,  5,  5,  5,  5,  0, -5,
		-10,  5,  5,  5,  5,  5,  0,-10,
		-10,  0,  5,  0,  0,  0,  0,-10,
		-20,-10,-10, -5, -5,-10,-10,-20
	};

	private static final double[] BLACKQUEENTABLE = {
		-20,-10,-10, -5, -5,-10,-10,-20,
		-10,  0,  0,  0,  0,  5,  0,-10,
		-10,  0,  5,  5,  5,  5,  5,-10,
		 -5,  0,  5,  5,  5,  5,  0,  0,
		 -5,  0,  5,  5,  5,  5,  0, -5,
		-10,  0,  5,  5,  5,  5,  0,-10,
		-10,  0,  0,  0,  0,  0,  0,-10,
		-20,-10,-10, -5, -5,-10,-10,-20

	};

	private static final double[] WHITEKINGMIDDLETABLE = {
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-20,-30,-30,-40,-40,-30,-30,-20,
		-10,-20,-20,-20,-20,-20,-20,-10,
		 20, 20,  0,  0,  0,  0, 20, 20,
		 20, 30, 10,  0,  0, 10, 30, 20
	};
	
	private static final double[] BLACKKINGMIDDLETABLE = {
		
		 20, 30, 10,  0,  0, 10, 30, 20,
		 20, 20,  0,  0,  0,  0, 20, 20,
		-10,-20,-20,-20,-20,-20,-20,-10,
		-20,-30,-30,-40,-40,-30,-30,-20,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30,
		-30,-40,-40,-50,-50,-40,-40,-30
	};


	private static final double[] WHITEKINGENDTABLE = {
		-50,-40,-30,-20,-20,-30,-40,-50,
		-30,-20,-10,  0,  0,-10,-20,-30,
		-30,-10, 20, 30, 30, 20,-10,-30,
		-30,-10, 30, 40, 40, 30,-10,-30,
		-30,-10, 30, 40, 40, 30,-10,-30,
		-30,-10, 20, 30, 30, 20,-10,-30,
		-30,-30,  0,  0,  0,  0,-30,-30,
		-50,-30,-30,-30,-30,-30,-30,-50
	};
	
	private static final double[] BLACKKINGENDTABLE = {
		-50,-30,-30,-30,-30,-30,-30,-50,
		-30,-30,  0,  0,  0,  0,-30,-30,
		-30,-10, 20, 30, 30, 20,-10,-30,
		-30,-10, 30, 40, 40, 30,-10,-30,
		-30,-10, 30, 40, 40, 30,-10,-30,
		-30,-10, 20, 30, 30, 20,-10,-30,
		-30,-20,-10,  0,  0,-10,-20,-30,
		-50,-40,-30,-20,-20,-30,-40,-50
	};
	
	/*Value for each possible move*/
	private static final double NROFMOVESVALUE = 10;
	/*Special pawn values*/
	private static final double BACKWARDPAWNVALUE = -50;
	private static final double DOUBLEDPAWNVALUE = -50;
	private static final double ISOLATEDPAWNVALUE = -50;
	/*Piece values*/
	private static final double PAWNVALUE = 100;
	private static final double ROOKVALUE = 500;
	private static final double BISHOPVALUE = 330;
	private static final double KNIGHTVALUE = 320;
	private static final double QUEENVALUE = 900;
	private static final double KINGVALUE = 20000;
	/*The end criteria which triggers the use of the end table 
	 * instead of the regular table for kings*/
	private static final double ENDCRITERIA = 1300;


	@Override
	public synchronized double evaluate(ChessPosition position) {
		double specialValue = 0;
		double whitePieceValue = 0;
		double blackPieceValue = 0;
		
		/*Stalemate equals a draw*/
//		if(position.isMate(position.getPlayerToMove()) == 2) {
//			return 0;
//		}
		
		/*Calculate the points given for the number of possible moves for each player*/
		specialValue += position.getLegalMoves(WHITE).size() * NROFMOVESVALUE;
		specialValue -= position.getLegalMoves(BLACK).size() * NROFMOVESVALUE;

		/*PAWNS*/
		BitSet whitePawns = position.getPawns(WHITE);
		BitSet blackPawns = position.getPawns(BLACK);
		/*Calculate the values for the pawns on the board*/
		whitePieceValue += whitePawns.cardinality() * PAWNVALUE;
		blackPieceValue -= blackPawns.cardinality() * PAWNVALUE;
		/*Calculate special pawn values*/
		for (int pawnLocation = whitePawns.nextSetBit(0); pawnLocation >= 0; pawnLocation = whitePawns
				.nextSetBit(pawnLocation + 1)) {

			specialValue += WHITEPAWNTABLE[pawnLocation];

			/*Doubled pawns*/
			BitSet pawnFile = (BitSet) ALL_FILES.get(pawnLocation).clone();
			pawnFile.and(whitePawns);
			if(pawnFile.cardinality() > 1) {
				specialValue += (pawnFile.cardinality() - 1) * DOUBLEDPAWNVALUE / 2;
			}

			/*Isolated pawns*/
			boolean isolated = true;


			if(!FILE_H.get(pawnLocation)) {
				BitSet rightFile = (BitSet) ALL_FILES.get(pawnLocation+1).clone();
				rightFile.and(whitePawns);
				if(rightFile.cardinality() > 0) {
					isolated = false;
				}
			}
			if(!FILE_A.get(pawnLocation)) {
				BitSet leftFile = (BitSet) ALL_FILES.get(pawnLocation-1).clone();
				leftFile.and(whitePawns);
				if(leftFile.cardinality() > 0) {
					isolated = false;
				}
			}
			if(isolated) {
				specialValue += ISOLATEDPAWNVALUE;
			}

			/*Backward pawns*/
			boolean backward = true;
			BitSet leftFile = (BitSet) ALL_FILES.get(pawnLocation-1).clone();
			BitSet rightFile = (BitSet) ALL_FILES.get(pawnLocation+1).clone();
			BitSet adjacentFiles = new BitSet();
			if(!FILE_H.get(pawnLocation)) {
				adjacentFiles.or(rightFile);
			}
			if(!FILE_A.get(pawnLocation)) {
				adjacentFiles.or(leftFile);
			}
			adjacentFiles.and(whitePawns);
			for (int bpLocation = adjacentFiles.nextSetBit(0); bpLocation >= 0; bpLocation = adjacentFiles
					.nextSetBit(bpLocation + 1)) {
				if(bpLocation > (pawnLocation - (pawnLocation % 8))) {
					backward = false;
				}
			}
			if(backward && !isolated) {
				specialValue += BACKWARDPAWNVALUE;
			}
		}

		for (int pawnLocation = blackPawns.nextSetBit(0); pawnLocation >= 0; pawnLocation = blackPawns
				.nextSetBit(pawnLocation + 1)) {

			specialValue -= BLACKPAWNTABLE[pawnLocation];


			/*Doubled pawns*/
			BitSet pawnFile = (BitSet) ALL_FILES.get(pawnLocation).clone();
			pawnFile.and(blackPawns);
			if(pawnFile.cardinality() > 1) {
				specialValue -= (pawnFile.cardinality()-1) * DOUBLEDPAWNVALUE/2;
			}

			/*Isolated pawns*/
			boolean isolated = true;
			if(!FILE_H.get(pawnLocation)) {
				BitSet rightFile = (BitSet) ALL_FILES.get(pawnLocation+1).clone();
				rightFile.and(blackPawns);
				if(rightFile.cardinality() > 0) {
					isolated = false;
				}
			}
			if(!FILE_A.get(pawnLocation)) {
				BitSet leftFile = (BitSet) ALL_FILES.get(pawnLocation-1).clone();
				leftFile.and(blackPawns);
				if(leftFile.cardinality() > 0) {
					isolated = false;
				}
			}
			if(isolated) {
				specialValue -= ISOLATEDPAWNVALUE;
			}
			/*Backward pawns*/
			boolean backward = true;
			BitSet leftFile = (BitSet) ALL_FILES.get(pawnLocation-1).clone();
			BitSet rightFile = (BitSet) ALL_FILES.get(pawnLocation+1).clone();
			BitSet adjacentFiles = new BitSet();
			if(!FILE_H.get(pawnLocation)) {
				adjacentFiles.or(rightFile);
			}
			if(!FILE_A.get(pawnLocation)) {
				adjacentFiles.or(leftFile);
			}
			adjacentFiles.and(blackPawns);
			for (int bpLocation = adjacentFiles.nextSetBit(0); bpLocation >= 0; bpLocation = adjacentFiles
					.nextSetBit(bpLocation + 1)) {
				if(bpLocation < (pawnLocation + (7-(pawnLocation % 8)))) {
					backward = false;
				}
			}
			if(backward && !isolated) {
				specialValue -= BACKWARDPAWNVALUE;
			}
		}

		/*ROOKS*/
		BitSet whiteRooks = position.getRooks(WHITE);
		BitSet blackRooks = position.getRooks(BLACK);
		whitePieceValue += whiteRooks.cardinality() * ROOKVALUE;
		blackPieceValue -= blackRooks.cardinality() * ROOKVALUE;
		for (int rookLocation = whiteRooks.nextSetBit(0); rookLocation >= 0; rookLocation = whiteRooks
				.nextSetBit(rookLocation + 1)) {
			specialValue += WHITEROOKTABLE[rookLocation];
		}
		for (int rookLocation = blackRooks.nextSetBit(0); rookLocation >= 0; rookLocation = blackRooks
				.nextSetBit(rookLocation + 1)) {
			specialValue -= BLACKROOKTABLE[rookLocation];
		}

		/*BISHOPS*/
		BitSet whiteBishops = position.getBishops(WHITE);
		BitSet blackBishops = position.getBishops(BLACK);
		whitePieceValue += whiteBishops.cardinality() * BISHOPVALUE;
		blackPieceValue -= blackBishops.cardinality() * BISHOPVALUE;
		for (int bishopLocation = whiteBishops.nextSetBit(0); bishopLocation >= 0; bishopLocation = whiteBishops
				.nextSetBit(bishopLocation + 1)) {
			specialValue += WHITEBISHOPTABLE[bishopLocation];
		}
		for (int bishopLocation = blackBishops.nextSetBit(0); bishopLocation >= 0; bishopLocation = blackBishops
				.nextSetBit(bishopLocation + 1)) {
			specialValue -= BLACKBISHOPTABLE[bishopLocation];
		}
		/*KNIGHTS*/
		BitSet whiteKnights = position.getKnights(WHITE);
		BitSet blackKnights = position.getKnights(BLACK);
		whitePieceValue += whiteKnights.cardinality() * KNIGHTVALUE;
		blackPieceValue -= blackKnights.cardinality() * KNIGHTVALUE;
		for (int knightLocation = whiteKnights.nextSetBit(0); knightLocation >= 0; knightLocation = whiteKnights
				.nextSetBit(knightLocation + 1)) {
			specialValue += WHITEKNIGHTTABLE[knightLocation];
		}
		for (int knightLocation = blackKnights.nextSetBit(0); knightLocation >= 0; knightLocation = blackKnights
				.nextSetBit(knightLocation + 1)) {
			specialValue -= BLACKKNIGHTTABLE[knightLocation];
		}

		/*QUEENS*/
		BitSet whiteQueens = position.getQueens(WHITE);
		BitSet blackQueens = position.getQueens(BLACK);
		whitePieceValue += whiteQueens.cardinality() * QUEENVALUE;
		blackPieceValue -= blackQueens.cardinality() * QUEENVALUE;
		for (int queenLocation = whiteQueens.nextSetBit(0); queenLocation >= 0; queenLocation = whiteQueens
				.nextSetBit(queenLocation + 1)) {
			specialValue += WHITEQUEENTABLE[queenLocation];
		}
		for (int queenLocation = blackQueens.nextSetBit(0); queenLocation >= 0; queenLocation = blackQueens
				.nextSetBit(queenLocation + 1)) {
			specialValue -= BLACKQUEENTABLE[queenLocation];
		}

		/*KING*/
		BitSet whiteKing = position.getKing(WHITE);
		BitSet blackKing = position.getKing(BLACK);
		for (int kingLocation = whiteKing.nextSetBit(0); kingLocation >= 0; kingLocation = whiteKing
				.nextSetBit(kingLocation + 1)) {
			if(whitePieceValue < ENDCRITERIA && blackPieceValue > -ENDCRITERIA) {
				specialValue += WHITEKINGENDTABLE[kingLocation];
			} else {
				specialValue += WHITEKINGMIDDLETABLE[kingLocation];
			}
		}
		for (int kingLocation = blackKing.nextSetBit(0); kingLocation >= 0; kingLocation = blackKing
				.nextSetBit(kingLocation + 1)) {
			if(whitePieceValue < ENDCRITERIA && blackPieceValue > -ENDCRITERIA) {
				specialValue -= BLACKKINGENDTABLE[kingLocation];
			} else {
				specialValue -= BLACKKINGMIDDLETABLE[kingLocation];
			}
		}
		whitePieceValue += whiteKing.cardinality() * KINGVALUE;
		blackPieceValue -= blackKing.cardinality() * KINGVALUE;

		return specialValue + whitePieceValue + blackPieceValue;
	}
}
