package insanechess.backend.constants;

import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Files {

    public static final BitSet FILE_A = new BitSet();
    public static final BitSet FILE_B = new BitSet();
    public static final BitSet FILE_C = new BitSet();
    public static final BitSet FILE_D = new BitSet();
    public static final BitSet FILE_E = new BitSet();
    public static final BitSet FILE_F = new BitSet();
    public static final BitSet FILE_G = new BitSet();
    public static final BitSet FILE_H = new BitSet();
    public static Map<Integer, BitSet> ALL_FILES = new HashMap<>();

    static {
        initializeFiles();
    }

    private static void initializeFiles() {
        for (int i = 0; i < 64; i += 8) {
            FILE_A.set(i);
            ALL_FILES.put(i, FILE_A);
        }
        for (int i = 1; i < 64; i += 8) {
            FILE_B.set(i);
            ALL_FILES.put(i, FILE_B);
        }
        for (int i = 2; i < 64; i += 8) {
            FILE_C.set(i);
            ALL_FILES.put(i, FILE_C);
        }
        for (int i = 3; i < 64; i += 8) {
            FILE_D.set(i);
            ALL_FILES.put(i, FILE_D);
        }
        for (int i = 4; i < 64; i += 8) {
            FILE_E.set(i);
            ALL_FILES.put(i, FILE_E);
        }
        for (int i = 5; i < 64; i += 8) {
            FILE_F.set(i);
            ALL_FILES.put(i, FILE_F);
        }
        for (int i = 6; i < 64; i += 8) {
            FILE_G.set(i);
            ALL_FILES.put(i, FILE_G);
        }
        for (int i = 7; i < 64; i += 8) {
            FILE_H.set(i);
            ALL_FILES.put(i, FILE_H);
        }
        ALL_FILES = Collections.unmodifiableMap(ALL_FILES);
    }


}
