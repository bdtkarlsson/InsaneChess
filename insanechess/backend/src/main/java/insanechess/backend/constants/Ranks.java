package insanechess.backend.constants;

import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Ranks {

    public static final BitSet RANK_8 = new BitSet();
    public static final BitSet RANK_7 = new BitSet();
    public static final BitSet RANK_6 = new BitSet();
    public static final BitSet RANK_5 = new BitSet();
    public static final BitSet RANK_4 = new BitSet();
    public static final BitSet RANK_3 = new BitSet();
    public static final BitSet RANK_2 = new BitSet();
    public static final BitSet RANK_1 = new BitSet();
    public static Map<Integer, BitSet> ALL_RANKS = new HashMap<>();

    static {
        initializeRanks();
    }

    private static void initializeRanks() {
        for (int i = 0; i < 8; i++) {
            RANK_8.set(i);
            ALL_RANKS.put(i, RANK_8);
        }
        for (int i = 8; i < 16; i++) {
            RANK_7.set(i);
            ALL_RANKS.put(i, RANK_7);
        }
        for (int i = 16; i < 24; i++) {
            RANK_6.set(i);
            ALL_RANKS.put(i, RANK_6);
        }
        for (int i = 24; i < 32; i++) {
            RANK_5.set(i);
            ALL_RANKS.put(i, RANK_5);
        }
        for (int i = 32; i < 40; i++) {
            RANK_4.set(i);
            ALL_RANKS.put(i, RANK_4);
        }
        for (int i = 40; i < 48; i++) {
            RANK_3.set(i);
            ALL_RANKS.put(i, RANK_3);
        }
        for (int i = 48; i < 56; i++) {
            RANK_2.set(i);
            ALL_RANKS.put(i, RANK_2);
        }
        for (int i = 56; i < 64; i++) {
            RANK_1.set(i);
            ALL_RANKS.put(i, RANK_1);
        }
        ALL_RANKS = Collections.unmodifiableMap(ALL_RANKS);
    }

}
