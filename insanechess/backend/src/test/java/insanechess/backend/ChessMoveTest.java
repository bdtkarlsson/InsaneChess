package insanechess.backend;


import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.junit.Assert.assertTrue;

public class ChessMoveTest {

    @Test
    public void testGetBitSet() {
        ChessMove move = new ChessMove(1, 20);
        BitSet bitSet = move.getBitSet();
        assertTrue(bitSet.get(1));
        assertTrue(bitSet.get(20));
    }
}
