package insanechess.backend.constants;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationsTest {

    @Test
    void positionAtIndexTest() {
        int i = 0;
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                int index = Validations.getIndexAtPosition(new Point(x, y));
                assertEquals(i, index);
                i++;
            }
        }
    }

    @Test
    void indexAtPositionTest() {
        int i = 0;
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                Point position = Validations.getPositionAtIndex(i);
                assertEquals(position, new Point(x, y));
                i++;
            }
        }
    }
}
