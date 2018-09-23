package insanechess.backend;

import insanechess.backend.constants.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChessPositionTest {


    @Test
    void whiteIsStartPlayerTest() {
        ChessPosition position = new ChessPosition();
        assertEquals(Player.WHITE, position.getPlayerToMove());
    }

    @Test
    void correctNumberOfPiecesAtStartTest() {
        ChessPosition position = new ChessPosition();
        for (Player player : Arrays.asList(Player.WHITE, Player.BLACK)) {
            assertEquals(1, position.getKing(player).cardinality());
            assertEquals(2, position.getRooks(player).cardinality());
            assertEquals(8, position.getPawns(player).cardinality());
            assertEquals(1, position.getQueens(player).cardinality());
            assertEquals(2, position.getBishops(player).cardinality());
            assertEquals(2, position.getKnights(player).cardinality());
            assertEquals(0, position.getEnPassants(player).cardinality());
            assertEquals(16, position.getAlliedPieces(player).cardinality());
            assertEquals(16, position.getOpponentPieces(player).cardinality());
        }
        assertEquals(32, position.getAllPieces().cardinality());
    }

    @Test
    void noMateAtStartTest() {
        ChessPosition position = new ChessPosition();
        for (Player player : Arrays.asList(Player.WHITE, Player.BLACK)) {
            assertEquals(0, position.isMate(player));
        }
    }

    @Test
    void castlingLegalAtStartTest() {
        ChessPosition position = new ChessPosition();
        assertTrue(position.isBlackLongCastlingLegal());
        assertTrue(position.isBlackShortCastlingLegal());
        assertTrue(position.isWhiteLongCastlingLegal());
        assertTrue(position.isWhiteShortCastlingLegal());
    }

    @Test
    void legalMovesAtStartTest() {
        ChessPosition position = new ChessPosition();
        for (Player player : Arrays.asList(Player.WHITE, Player.BLACK)) {
            //16 pawn moves, 4 knight moves
            assertEquals(20, position.getLegalMoves(player).size());
        }
    }
}
