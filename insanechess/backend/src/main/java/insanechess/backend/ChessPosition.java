package insanechess.backend;

import insanechess.backend.constants.Player;
import insanechess.backend.pieces.*;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Objects;

import static insanechess.backend.constants.Player.BLACK;
import static insanechess.backend.constants.Player.WHITE;
import static insanechess.backend.constants.Ranks.*;


public class ChessPosition {
	private Player playerToMove;

	private BitSet whiteRooks;
	private BitSet whiteBishops;
	private BitSet whiteKnights;
	private BitSet whiteQueens;
	private BitSet whiteKing;
	private BitSet whitePawns;
	private BitSet whiteEnPassants;

	private BitSet blackRooks;
	private BitSet blackBishops;
	private BitSet blackKnights;
	private BitSet blackQueens;
	private BitSet blackKing;
	private BitSet blackPawns;
	private BitSet blackEnPassants;

	private BitSet whiteLegalLocations;
	private BitSet blackLegalLocations;

	private ArrayList<ChessMove> whiteLegalMoves;
	private ArrayList<ChessMove> blackLegalMoves;

	private boolean whiteLongCastlingLegal;
	private boolean whiteShortCastlingLegal;
	private boolean blackLongCastlingLegal;
	private boolean blackShortCastlingLegal;

	public ChessPosition() {
		initializePieces();
		clearMovesAndLocations();
		whiteLongCastlingLegal = true;
		whiteShortCastlingLegal = true;
		blackLongCastlingLegal = true;
		blackShortCastlingLegal = true;
		playerToMove = WHITE;
		calculateLegalMoves();
	}

	public ChessPosition(ChessPosition another) {
		whiteLegalLocations = another.getLegalLocations(WHITE);
		blackLegalLocations = another.getLegalLocations(BLACK);
		whiteLegalMoves = new ArrayList<>(another.getLegalMoves(WHITE));
		blackLegalMoves = new ArrayList<>(another.getLegalMoves(BLACK));
		blackRooks = (BitSet) another.getRooks(BLACK).clone();
		whiteRooks = (BitSet) another.getRooks(WHITE).clone();
		blackBishops = (BitSet) another.getBishops(BLACK).clone();
		whiteBishops = (BitSet) another.getBishops(WHITE).clone();
		blackKnights = (BitSet) another.getKnights(BLACK).clone();
		whiteKnights = (BitSet) another.getKnights(WHITE).clone();
		blackPawns = (BitSet) another.getPawns(BLACK).clone();
		whitePawns = (BitSet) another.getPawns(WHITE).clone();
		blackKing = (BitSet) another.getKing(BLACK).clone();
		whiteKing = (BitSet) another.getKing(WHITE).clone();
		blackQueens = (BitSet) another.getQueens(BLACK).clone();
		whiteQueens = (BitSet) another.getQueens(WHITE).clone();
		//En passants only exists for one turn, therefor they are not
		//copied into the new model
		whiteEnPassants = new BitSet();
		blackEnPassants = new BitSet();
		playerToMove = another.getPlayerToMove();
		whiteLongCastlingLegal = another.isWhiteLongCastlingLegal();
		whiteShortCastlingLegal = another.isWhiteShortCastlingLegal();
		blackLongCastlingLegal = another.isBlackLongCastlingLegal();
		blackShortCastlingLegal = another.isBlackShortCastlingLegal();
	}

	private void initializePieces() {
		whiteRooks = new BitSet();
		whiteRooks.set(56);
		whiteRooks.set(63);
		whiteKnights = new BitSet();
		whiteKnights.set(57);
		whiteKnights.set(62);
		whiteBishops = new BitSet();
		whiteBishops.set(58);
		whiteBishops.set(61);
		whiteQueens = new BitSet();
		whiteQueens.set(59);
		whitePawns = new BitSet();
		whitePawns.set(48);
		whitePawns.set(49);
		whitePawns.set(50);
		whitePawns.set(51);
		whitePawns.set(52);
		whitePawns.set(53);
		whitePawns.set(54);
		whitePawns.set(55);
		whiteKing = new BitSet();
		whiteKing.set(60);
		whiteEnPassants = new BitSet();

		blackRooks = new BitSet();
		blackRooks.set(0);
		blackRooks.set(7);
		blackKnights = new BitSet();
		blackKnights.set(1);
		blackKnights.set(6);
		blackBishops = new BitSet();
		blackBishops.set(2);
		blackBishops.set(5);
		blackQueens = new BitSet();
		blackQueens.set(3);
		blackPawns = new BitSet();
		blackPawns.set(8);
		blackPawns.set(9);
		blackPawns.set(10);
		blackPawns.set(11);
		blackPawns.set(12);
		blackPawns.set(13);
		blackPawns.set(14);
		blackPawns.set(15);
		blackKing = new BitSet();
		blackKing.set(4);
		blackEnPassants = new BitSet();
	}

	public synchronized ChessPosition makeMove(ChessMove move) {

		BitSet moveBitSet = move.getBitSet();
		ChessPosition modelCopy = new ChessPosition(this);

		//If player is white and move is legal
		if(playerToMove == WHITE && whiteLegalMoves.contains(move)) {
			if(whiteRooks.get(move.getFrom())) {
				moveWhiteRook(move, moveBitSet, modelCopy);
			} else if(whiteKnights.get(move.getFrom())) {
				moveKnight(moveBitSet, modelCopy, WHITE);
			} else if(whiteBishops.get(move.getFrom())) {
				moveBishop(moveBitSet, modelCopy, WHITE);
			} else if (whiteKing.get(move.getFrom()) && !isWhiteCastlingAttempt(move)) {
				moveWhiteKing(moveBitSet, modelCopy);
			} else if(whiteKing.get(move.getFrom()) && whiteShortCastlingPossible(move)) {
				moveWhiteKingShortCastling(modelCopy);
			} else if(whiteKing.get(move.getFrom()) && whiteLongCastlingPossible(move)) {
				moveWhiteKingLongCastling(modelCopy);
			} else if(whiteQueens.get(move.getFrom())) {
				moveQueen(moveBitSet, modelCopy, WHITE);
			} else if(whitePawns.get(move.getFrom())) {
				moveWhitePawn(move, moveBitSet, modelCopy);
			} else {
				return null;
			}
			removeBlackAttackedPieces(move, modelCopy);
		} else if (playerToMove == BLACK && blackLegalMoves.contains(move)) {
			if(blackRooks.get(move.getFrom())) {
				moveBlackRook(move, moveBitSet, modelCopy);
			} else if(blackKnights.get(move.getFrom())) {
				moveKnight(moveBitSet, modelCopy, BLACK);
			} else if(blackBishops.get(move.getFrom())) {
				moveBishop(moveBitSet, modelCopy, BLACK);
			} else if (blackKing.get(move.getFrom()) && !isBlackCastlingAttempt(move)) {
				moveBlackKing(moveBitSet, modelCopy);
			}  else if(blackKing.get(move.getFrom()) && BlackShortCastlingPossible(move)) {
				moveBlackKingShortCastling(modelCopy);
			} else if(blackKing.get(move.getFrom()) && BlackLongCastlingPossible(move)) {
				moveBlackKingLongCastling(modelCopy);
			} else if(blackQueens.get(move.getFrom())) {
				moveQueen(moveBitSet, modelCopy, BLACK);
			} else if(blackPawns.get(move.getFrom())) {
				moveBlackPawn(move, moveBitSet, modelCopy);
			} else {
				return null;
			}
			removeWhiteAttackedPieces(move, modelCopy);
		} else {
			return null;
		}

		//If move resulted in check for player to move, the move is not legal
		modelCopy.calculateLegalMoves();
		if(modelCopy.isCheck(playerToMove)) {
			return null;
		}
		//Move was legal, switch player and return next position
		modelCopy.switchPlayerToMove();
		return modelCopy;
	}

	private boolean BlackLongCastlingPossible(ChessMove move) {
		return move.getFrom() == 4 && move.getTo() == 0 && isBlackLongCastlingLegal()
				&& !whiteLegalLocations.get(4) && !whiteLegalLocations.get(3) && !whiteLegalLocations.get(2);
	}

	private boolean BlackShortCastlingPossible(ChessMove move) {
		return move.getFrom() == 4 && move.getTo() == 7 && isBlackShortCastlingLegal()
				&& !whiteLegalLocations.get(4) && !whiteLegalLocations.get(5)
				&& !whiteLegalLocations.get(6);
	}

	private boolean isBlackCastlingAttempt(ChessMove move) {
		return move.getFrom() == 4 && (move.getTo() == 7 ||
				move.getTo() == 0);
	}

	private boolean isWhiteCastlingAttempt(ChessMove move) {
		return move.getFrom() == 60 && (move.getTo() == 63 || move.getTo() == 56);
	}

	private boolean whiteLongCastlingPossible(ChessMove move) {
		return isWhiteLongCastlingLegal() && move.getFrom() == 60 && move.getTo() == 63
				&& !blackLegalLocations.get(58) && !blackLegalLocations.get(59)
				&& !blackLegalLocations.get(60);
	}

	private boolean whiteShortCastlingPossible(ChessMove move) {
		return isWhiteShortCastlingLegal() && move.getFrom() == 60 && move.getTo() == 63
				&& !blackLegalLocations.get(60) && !blackLegalLocations.get(61) &&
				!blackLegalLocations.get(62);
	}

	private void removeWhiteAttackedPieces(ChessMove move, ChessPosition modelCopy) {
		if(whiteRooks.get(move.getTo())) {
			modelCopy.getRooks(WHITE).flip(move.getTo());
		} else if(whiteKnights.get(move.getTo())) {
			modelCopy.getKnights(WHITE).flip(move.getTo());
		} else if(whiteBishops.get(move.getTo())) {
			modelCopy.getBishops(WHITE).flip(move.getTo());
		} else if(whiteKing.get(move.getTo())) {
			modelCopy.getKing(WHITE).flip(move.getTo());
		} else if(whiteQueens.get(move.getTo())) {
			modelCopy.getQueens(WHITE).flip(move.getTo());
		} else if(whitePawns.get(move.getTo())) {
			modelCopy.getPawns(WHITE).flip(move.getTo());
		}
	}

	private void moveBlackPawn(ChessMove move, BitSet moveBitSet, ChessPosition modelCopy) {
		if(RANK_1.get(move.getTo())) {// promotion
			modelCopy.getPawns(BLACK).flip(move.getFrom());
			modelCopy.getQueens(BLACK).flip(move.getTo());
		} else if(RANK_7.get(move.getFrom())
				&& RANK_5.get(move.getTo())) {// en passant created
			modelCopy.getPawns(BLACK).xor(moveBitSet);
			modelCopy.getEnPassants(BLACK).set(move.getFrom() + 8);
		}  else {// regular pawn move
			modelCopy.getPawns(BLACK).xor(moveBitSet);
			if(getEnPassants(WHITE).get(move.getTo())) {// en passant attacked
				modelCopy.getPawns(WHITE).flip(move.getTo() - 8);
			}
		}
	}

	private void moveBlackKingLongCastling(ChessPosition modelCopy) {
		modelCopy.getKing(BLACK).flip(4);
		modelCopy.getKing(BLACK).flip(2);
		modelCopy.getRooks(BLACK).flip(0);
		modelCopy.getRooks(BLACK).flip(3);
		modelCopy.setBlackLongCastlingLegal(false);
		modelCopy.setBlackShortCastlingLegal(false);
	}

	private void moveBlackKingShortCastling(ChessPosition modelCopy) {
		modelCopy.getKing(BLACK).flip(4);
		modelCopy.getKing(BLACK).flip(6);
		modelCopy.getRooks(BLACK).flip(7);
		modelCopy.getRooks(BLACK).flip(5);
		modelCopy.setBlackLongCastlingLegal(false);
		modelCopy.setBlackShortCastlingLegal(false);
	}

	private void moveBlackKing(BitSet moveBitSet, ChessPosition modelCopy) {
		modelCopy.getKing(BLACK).xor(moveBitSet);
		modelCopy.setBlackLongCastlingLegal(false);
		modelCopy.setBlackShortCastlingLegal(false);
	}

	private void moveBlackRook(ChessMove move, BitSet moveBitSet, ChessPosition modelCopy) {
		modelCopy.getRooks(BLACK).xor(moveBitSet);
		if(move.getFrom() == 7) {// disable short castling
			modelCopy.setBlackShortCastlingLegal(false);
		} else if(move.getFrom() == 0) {// disable long castling
			modelCopy.setBlackLongCastlingLegal(false);
		}
	}

	private void removeBlackAttackedPieces(ChessMove move, ChessPosition modelCopy) {
		if(blackRooks.get(move.getTo())) {
			modelCopy.getRooks(BLACK).flip(move.getTo());
		} else if(blackKnights.get(move.getTo())) {
			modelCopy.getKnights(BLACK).flip(move.getTo());
		} else if(blackBishops.get(move.getTo())) {
			modelCopy.getBishops(BLACK).flip(move.getTo());
		} else if(blackKing.get(move.getTo())) {
			modelCopy.getKing(BLACK).flip(move.getTo());
		} else if(blackQueens.get(move.getTo())) {
			modelCopy.getQueens(BLACK).flip(move.getTo());
		} else if(blackPawns.get(move.getTo())) {
			modelCopy.getPawns(BLACK).flip(move.getTo());
		}
	}

	private void moveWhitePawn(ChessMove move, BitSet moveBitSet, ChessPosition modelCopy) {
		if(RANK_8.get(move.getTo())) {// promotion
			modelCopy.getPawns(WHITE).flip(move.getFrom());
			modelCopy.getQueens(WHITE).flip(move.getTo());
		} else if(RANK_2.get(move.getFrom()) && RANK_4.get(move.getTo())) { // en passant create
			modelCopy.getPawns(WHITE).xor(moveBitSet);
			modelCopy.getEnPassants(WHITE).set(move.getFrom() - 8);
		} else {// regular pawn move
			modelCopy.getPawns(WHITE).xor(moveBitSet);
			if(getEnPassants(BLACK).get(move.getTo())) {// en passant attacked
				modelCopy.getPawns(BLACK).flip(move.getTo() + 8);
			}
		}
	}

	private void moveQueen(BitSet moveBitSet, ChessPosition modelCopy, Player white) {
		modelCopy.getQueens(white).xor(moveBitSet);
	}

	private void moveWhiteKingLongCastling(ChessPosition modelCopy) {
		modelCopy.getKing(WHITE).flip(60);
		modelCopy.getKing(WHITE).flip(58);
		modelCopy.getRooks(WHITE).flip(56);
		modelCopy.getRooks(WHITE).flip(59);
		modelCopy.setWhiteLongCastlingLegal(false);
		modelCopy.setWhiteShortCastlingLegal(false);
	}

	private void moveWhiteKingShortCastling(ChessPosition modelCopy) {
		modelCopy.getKing(WHITE).flip(60);
		modelCopy.getKing(WHITE).flip(62);
		modelCopy.getRooks(WHITE).flip(63);
		modelCopy.getRooks(WHITE).flip(61);
		modelCopy.setWhiteLongCastlingLegal(false);
		modelCopy.setWhiteShortCastlingLegal(false);
	}

	private void moveWhiteKing(BitSet moveBitSet, ChessPosition modelCopy) {
		modelCopy.getKing(WHITE).xor(moveBitSet);
		modelCopy.setWhiteLongCastlingLegal(false);
		modelCopy.setWhiteShortCastlingLegal(false);
	}

	private void moveBishop(BitSet moveBitSet, ChessPosition modelCopy, Player white) {
		modelCopy.getBishops(white).xor(moveBitSet);
	}

	private void moveKnight(BitSet moveBitSet, ChessPosition modelCopy, Player white) {
		modelCopy.getKnights(white).xor(moveBitSet);
	}

	private void moveWhiteRook(ChessMove move, BitSet moveBitSet, ChessPosition modelCopy) {
		modelCopy.getRooks(WHITE).xor(moveBitSet);
		if(move.getFrom() == 56) {
			modelCopy.setWhiteLongCastlingLegal(false);
		} else if(move.getFrom() == 63) {
			modelCopy.setWhiteShortCastlingLegal(false);
		}
	}

	private void calculateLegalMoves() {
		clearMovesAndLocations();
		if (playerToMove == WHITE) {
			King.calculateLegalMoves(WHITE, this);
			Queen.calculateLegalMoves(WHITE, this);
			Bishop.calculateLegalMoves(WHITE, this);
			Knight.calculateLegalMoves(WHITE, this);
			Rook.calculateLegalMoves(WHITE, this);
			Pawn.calculateLegalMoves(WHITE, this);
		} else {
			King.calculateLegalMoves(BLACK, this);
			Queen.calculateLegalMoves(BLACK, this);
			Bishop.calculateLegalMoves(BLACK, this);
			Knight.calculateLegalMoves(BLACK, this);
			Rook.calculateLegalMoves(BLACK, this);
			Pawn.calculateLegalMoves(BLACK, this);
		}
	}

	private void clearMovesAndLocations() {
		whiteLegalLocations = new BitSet();
		blackLegalLocations = new BitSet();
		blackLegalMoves = new ArrayList<>();
		whiteLegalMoves = new ArrayList<>();
	}

	private boolean isCheck(Player player) {
		int kingPosition;
		if(player == WHITE) {
			kingPosition = whiteKing.nextSetBit(0);
			if(kingPosition != -1) {
				return blackLegalLocations.get(kingPosition);
			}
		} else {
			kingPosition = blackKing.nextSetBit(0);
			if(kingPosition != -1) {
				return whiteLegalLocations.get(kingPosition);
			}
		}
		return false;
	}

	public int isMate(Player player) {
		ChessPosition modelCopy = new ChessPosition(this);
		boolean mate = true;

		if(modelCopy.playerToMove != player) {
			modelCopy.switchPlayerToMove();
		}
		for(ChessMove move : modelCopy.getLegalMoves(player)) {
			if(modelCopy.makeMove(move) != null) {
				mate = false;
			}
		}

		if(mate) {
			if(isCheck(player)) { //checkmate
				return 1;
			} else { //stalemate
				return 2;
			}
		} else { //neither checkmate or stalemate
			return 0;
		}

	}

	public BitSet getWhitePieces() {
		final BitSet allWhitePieces = new BitSet();
		allWhitePieces.or(whiteRooks);
		allWhitePieces.or(whiteKnights);
		allWhitePieces.or(whiteBishops);
		allWhitePieces.or(whiteQueens);
		allWhitePieces.or(whitePawns);
		allWhitePieces.or(whiteKing);
		return allWhitePieces;
	}

	public BitSet getBlackPieces() {
		final BitSet allBlackPieces = new BitSet();
		allBlackPieces.or(blackRooks);
		allBlackPieces.or(blackKnights);
		allBlackPieces.or(blackBishops);
		allBlackPieces.or(blackQueens);
		allBlackPieces.or(blackPawns);
		allBlackPieces.or(blackKing);
		return allBlackPieces;
	}

	public BitSet getAllPieces() {
		final BitSet allPieces = new BitSet();
		allPieces.or(getBlackPieces());
		allPieces.or(getWhitePieces());
		return allPieces;
	}

	public BitSet getAlliedPieces(Player player) {
		if(player == WHITE) {
			return getWhitePieces();
		} else {
			return getBlackPieces();
		}
	}

	public BitSet getOpponentPieces(Player player) {
        if(player == BLACK) {
			return getWhitePieces();
		} else {
			return getBlackPieces();
		}
	}

	public BitSet getKing(Player player) {
        if(player == WHITE) {
			return whiteKing;
		} else {
			return blackKing;
		}
	}

	public BitSet getRooks(Player player) {
        if(player == WHITE) {
			return whiteRooks;
		} else {
			return blackRooks;
		}
	}

	public void setRooks(Player player, BitSet rooks) {
		if(player == WHITE) {
			whiteRooks = rooks;
		} else {
			blackRooks = rooks;
		}
	}

	public BitSet getBishops(Player player) {
		if(player == WHITE) {
			return whiteBishops;
		} else {
			return blackBishops;
		}
	}

	public void setBishops(Player player, BitSet bishops) {
		if(player == WHITE) {
			whiteBishops = bishops;
		} else {
			blackBishops = bishops;
		}
	}

	public BitSet getQueens(Player player) {
        if(player == WHITE) {
			return whiteQueens;
		} else {
			return blackQueens;
		}
	}

	public BitSet getKnights(Player player) {
        if(player == WHITE) {
			return whiteKnights;
		} else {
			return blackKnights;
		}
	}

	public BitSet getPawns(Player player) {
        if(player == WHITE) {
			return whitePawns;
		} else {
			return blackPawns;
		}
	}

	public BitSet getEnPassants(Player player) {
        if(player == WHITE) {
			return whiteEnPassants;
		} else {
			return blackEnPassants;
		}
	}

	public void addLegalLocation(Player player, int i) {
		if(player == WHITE) {
			whiteLegalLocations.set(i);
		} else {
			blackLegalLocations.set(i);
		}
	}

	private BitSet getLegalLocations(Player player) {
		if(player == WHITE) {
			return whiteLegalLocations;
		} else {
			return blackLegalLocations;
		}
	}

	public void addLegalMove(Player player, ChessMove move) {
		if(player == WHITE) {
			whiteLegalMoves.add(move);
		} else {
			blackLegalMoves.add(move);
		}
	}

	public ArrayList<ChessMove> getLegalMoves(Player player) {
		if(player == WHITE) {
			return whiteLegalMoves;
		} else {
			return blackLegalMoves;
		}
	}

	private void switchPlayerToMove() {
		playerToMove = (playerToMove == WHITE) ? BLACK : WHITE;
		calculateLegalMoves();
	}

	public synchronized Player getPlayerToMove() {
		return playerToMove;
	}

	public boolean isWhiteLongCastlingLegal() {
		return whiteLongCastlingLegal;
	}

	private void setWhiteLongCastlingLegal(boolean whiteLongCastlingLegal) {
		this.whiteLongCastlingLegal = whiteLongCastlingLegal;
	}

	public boolean isWhiteShortCastlingLegal() {
		return whiteShortCastlingLegal;
	}

    private void setWhiteShortCastlingLegal(boolean whiteShortCastlingLegal) {
		this.whiteShortCastlingLegal = whiteShortCastlingLegal;
	}

	public boolean isBlackLongCastlingLegal() {
		return blackLongCastlingLegal;
	}

    private void setBlackLongCastlingLegal(boolean blackLongCastlingLegal) {
		this.blackLongCastlingLegal = blackLongCastlingLegal;
	}

	public boolean isBlackShortCastlingLegal() {
		return blackShortCastlingLegal;
	}

    private void setBlackShortCastlingLegal(boolean blackShortCastlingLegal) {
		this.blackShortCastlingLegal = blackShortCastlingLegal;
	}

	@Override
	public String toString() {
		return getAllPieces().toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerToMove, whiteRooks, whiteBishops, whiteKnights, whiteQueens, whiteKing, whitePawns, whiteEnPassants, blackRooks, blackBishops, blackKnights, blackQueens, blackKing, blackPawns, blackEnPassants, whiteLegalLocations, blackLegalLocations, whiteLegalMoves, blackLegalMoves, whiteLongCastlingLegal, whiteShortCastlingLegal, blackLongCastlingLegal, blackShortCastlingLegal);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChessPosition that = (ChessPosition) o;
		return playerToMove == that.playerToMove &&
				whiteLongCastlingLegal == that.whiteLongCastlingLegal &&
				whiteShortCastlingLegal == that.whiteShortCastlingLegal &&
				blackLongCastlingLegal == that.blackLongCastlingLegal &&
				blackShortCastlingLegal == that.blackShortCastlingLegal &&
				Objects.equals(whiteRooks, that.whiteRooks) &&
				Objects.equals(whiteBishops, that.whiteBishops) &&
				Objects.equals(whiteKnights, that.whiteKnights) &&
				Objects.equals(whiteQueens, that.whiteQueens) &&
				Objects.equals(whiteKing, that.whiteKing) &&
				Objects.equals(whitePawns, that.whitePawns) &&
				Objects.equals(whiteEnPassants, that.whiteEnPassants) &&
				Objects.equals(blackRooks, that.blackRooks) &&
				Objects.equals(blackBishops, that.blackBishops) &&
				Objects.equals(blackKnights, that.blackKnights) &&
				Objects.equals(blackQueens, that.blackQueens) &&
				Objects.equals(blackKing, that.blackKing) &&
				Objects.equals(blackPawns, that.blackPawns) &&
				Objects.equals(blackEnPassants, that.blackEnPassants) &&
				Objects.equals(whiteLegalLocations, that.whiteLegalLocations) &&
				Objects.equals(blackLegalLocations, that.blackLegalLocations) &&
				Objects.equals(whiteLegalMoves, that.whiteLegalMoves) &&
				Objects.equals(blackLegalMoves, that.blackLegalMoves);
	}
}
