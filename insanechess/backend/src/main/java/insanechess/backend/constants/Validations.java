package insanechess.backend.constants;

import java.awt.*;

public class Validations {

    private static final Point[] indexToPosition = new Point[64];
    private static final int[][] positionToIndex = new int[8][8];

    static {
        int i = 0;
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                indexToPosition[i] = new Point(x, y);
                positionToIndex[y][x] = i;
                i++;
            }
        }
    }

    public static boolean isValidTile(final int moveLocation) {
        return moveLocation >= 0 && moveLocation < 64;
    }
    public static Point getPositionAtIndex(int i) {
        return indexToPosition[i];
    }
    public static int getIndexAtPosition(Point p) {
        return positionToIndex[p.y][p.x];
    }
}
