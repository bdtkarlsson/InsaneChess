package insanechess.backend.constants;

import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Diagonals {

    public static final BitSet RIGHT_DIAGONAL_1 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_2 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_3 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_4 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_5 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_6 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_7 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_8 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_9 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_10 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_11 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_12 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_13 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_14 = new BitSet();
    public static final BitSet RIGHT_DIAGONAL_15 = new BitSet();
    public static Map<Integer, BitSet> ALL_RIGHT_DIAGONALS = new HashMap<>();

    public static final BitSet LEFT_DIAGONAL_1 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_2 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_3 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_4 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_5 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_6 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_7 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_8 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_9 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_10 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_11 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_12 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_13 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_14 = new BitSet();
    public static final BitSet LEFT_DIAGONAL_15 = new BitSet();
    public static Map<Integer, BitSet> ALL_LEFT_DIAGONALS = new HashMap<>();


    static {
        initializeLeftDiagonals();
        initializeRightDiagonals();

    }

    private static void initializeLeftDiagonals() {
        LEFT_DIAGONAL_1.set(7);
        LEFT_DIAGONAL_2.set(6);
        LEFT_DIAGONAL_2.set(15);
        LEFT_DIAGONAL_3.set(5);
        LEFT_DIAGONAL_3.set(14);
        LEFT_DIAGONAL_3.set(23);
        LEFT_DIAGONAL_4.set(4);
        LEFT_DIAGONAL_4.set(13);
        LEFT_DIAGONAL_4.set(22);
        LEFT_DIAGONAL_4.set(31);
        LEFT_DIAGONAL_5.set(3);
        LEFT_DIAGONAL_5.set(12);
        LEFT_DIAGONAL_5.set(21);
        LEFT_DIAGONAL_5.set(30);
        LEFT_DIAGONAL_5.set(39);
        LEFT_DIAGONAL_6.set(2);
        LEFT_DIAGONAL_6.set(11);
        LEFT_DIAGONAL_6.set(20);
        LEFT_DIAGONAL_6.set(29);
        LEFT_DIAGONAL_6.set(38);
        LEFT_DIAGONAL_6.set(47);
        LEFT_DIAGONAL_7.set(1);
        LEFT_DIAGONAL_7.set(10);
        LEFT_DIAGONAL_7.set(19);
        LEFT_DIAGONAL_7.set(28);
        LEFT_DIAGONAL_7.set(37);
        LEFT_DIAGONAL_7.set(46);
        LEFT_DIAGONAL_7.set(55);
        LEFT_DIAGONAL_8.set(0);
        LEFT_DIAGONAL_8.set(9);
        LEFT_DIAGONAL_8.set(18);
        LEFT_DIAGONAL_8.set(27);
        LEFT_DIAGONAL_8.set(36);
        LEFT_DIAGONAL_8.set(45);
        LEFT_DIAGONAL_8.set(54);
        LEFT_DIAGONAL_8.set(63);
        LEFT_DIAGONAL_9.set(8);
        LEFT_DIAGONAL_9.set(17);
        LEFT_DIAGONAL_9.set(26);
        LEFT_DIAGONAL_9.set(35);
        LEFT_DIAGONAL_9.set(44);
        LEFT_DIAGONAL_9.set(53);
        LEFT_DIAGONAL_9.set(62);
        LEFT_DIAGONAL_10.set(16);
        LEFT_DIAGONAL_10.set(25);
        LEFT_DIAGONAL_10.set(34);
        LEFT_DIAGONAL_10.set(43);
        LEFT_DIAGONAL_10.set(52);
        LEFT_DIAGONAL_10.set(61);
        LEFT_DIAGONAL_11.set(24);
        LEFT_DIAGONAL_11.set(33);
        LEFT_DIAGONAL_11.set(42);
        LEFT_DIAGONAL_11.set(51);
        LEFT_DIAGONAL_11.set(60);
        LEFT_DIAGONAL_12.set(32);
        LEFT_DIAGONAL_12.set(41);
        LEFT_DIAGONAL_12.set(50);
        LEFT_DIAGONAL_12.set(59);
        LEFT_DIAGONAL_13.set(40);
        LEFT_DIAGONAL_13.set(49);
        LEFT_DIAGONAL_13.set(58);
        LEFT_DIAGONAL_14.set(48);
        LEFT_DIAGONAL_14.set(57);
        LEFT_DIAGONAL_15.set(56);
        ALL_LEFT_DIAGONALS.put(7, LEFT_DIAGONAL_1);
        ALL_LEFT_DIAGONALS.put(6, LEFT_DIAGONAL_2);
        ALL_LEFT_DIAGONALS.put(15, LEFT_DIAGONAL_2);
        ALL_LEFT_DIAGONALS.put(5, LEFT_DIAGONAL_3);
        ALL_LEFT_DIAGONALS.put(14, LEFT_DIAGONAL_3);
        ALL_LEFT_DIAGONALS.put(23, LEFT_DIAGONAL_3);
        ALL_LEFT_DIAGONALS.put(4, LEFT_DIAGONAL_4);
        ALL_LEFT_DIAGONALS.put(13, LEFT_DIAGONAL_4);
        ALL_LEFT_DIAGONALS.put(22, LEFT_DIAGONAL_4);
        ALL_LEFT_DIAGONALS.put(31, LEFT_DIAGONAL_4);
        ALL_LEFT_DIAGONALS.put(3, LEFT_DIAGONAL_5);
        ALL_LEFT_DIAGONALS.put(12, LEFT_DIAGONAL_5);
        ALL_LEFT_DIAGONALS.put(21, LEFT_DIAGONAL_5);
        ALL_LEFT_DIAGONALS.put(30, LEFT_DIAGONAL_5);
        ALL_LEFT_DIAGONALS.put(39, LEFT_DIAGONAL_5);
        ALL_LEFT_DIAGONALS.put(2, LEFT_DIAGONAL_6);
        ALL_LEFT_DIAGONALS.put(11, LEFT_DIAGONAL_6);
        ALL_LEFT_DIAGONALS.put(20, LEFT_DIAGONAL_6);
        ALL_LEFT_DIAGONALS.put(29, LEFT_DIAGONAL_6);
        ALL_LEFT_DIAGONALS.put(38, LEFT_DIAGONAL_6);
        ALL_LEFT_DIAGONALS.put(47, LEFT_DIAGONAL_6);
        ALL_LEFT_DIAGONALS.put(1, LEFT_DIAGONAL_7);
        ALL_LEFT_DIAGONALS.put(10, LEFT_DIAGONAL_7);
        ALL_LEFT_DIAGONALS.put(19, LEFT_DIAGONAL_7);
        ALL_LEFT_DIAGONALS.put(28, LEFT_DIAGONAL_7);
        ALL_LEFT_DIAGONALS.put(37, LEFT_DIAGONAL_7);
        ALL_LEFT_DIAGONALS.put(46, LEFT_DIAGONAL_7);
        ALL_LEFT_DIAGONALS.put(55, LEFT_DIAGONAL_7);
        ALL_LEFT_DIAGONALS.put(0, LEFT_DIAGONAL_8);
        ALL_LEFT_DIAGONALS.put(9, LEFT_DIAGONAL_8);
        ALL_LEFT_DIAGONALS.put(18, LEFT_DIAGONAL_8);
        ALL_LEFT_DIAGONALS.put(27, LEFT_DIAGONAL_8);
        ALL_LEFT_DIAGONALS.put(36, LEFT_DIAGONAL_8);
        ALL_LEFT_DIAGONALS.put(45, LEFT_DIAGONAL_8);
        ALL_LEFT_DIAGONALS.put(54, LEFT_DIAGONAL_8);
        ALL_LEFT_DIAGONALS.put(63, LEFT_DIAGONAL_8);
        ALL_LEFT_DIAGONALS.put(8, LEFT_DIAGONAL_9);
        ALL_LEFT_DIAGONALS.put(17, LEFT_DIAGONAL_9);
        ALL_LEFT_DIAGONALS.put(26, LEFT_DIAGONAL_9);
        ALL_LEFT_DIAGONALS.put(35, LEFT_DIAGONAL_9);
        ALL_LEFT_DIAGONALS.put(44, LEFT_DIAGONAL_9);
        ALL_LEFT_DIAGONALS.put(53, LEFT_DIAGONAL_9);
        ALL_LEFT_DIAGONALS.put(62, LEFT_DIAGONAL_9);
        ALL_LEFT_DIAGONALS.put(16, LEFT_DIAGONAL_10);
        ALL_LEFT_DIAGONALS.put(25, LEFT_DIAGONAL_10);
        ALL_LEFT_DIAGONALS.put(34, LEFT_DIAGONAL_10);
        ALL_LEFT_DIAGONALS.put(43, LEFT_DIAGONAL_10);
        ALL_LEFT_DIAGONALS.put(52, LEFT_DIAGONAL_10);
        ALL_LEFT_DIAGONALS.put(61, LEFT_DIAGONAL_10);
        ALL_LEFT_DIAGONALS.put(24, LEFT_DIAGONAL_11);
        ALL_LEFT_DIAGONALS.put(33, LEFT_DIAGONAL_11);
        ALL_LEFT_DIAGONALS.put(42, LEFT_DIAGONAL_11);
        ALL_LEFT_DIAGONALS.put(51, LEFT_DIAGONAL_11);
        ALL_LEFT_DIAGONALS.put(60, LEFT_DIAGONAL_11);
        ALL_LEFT_DIAGONALS.put(32, LEFT_DIAGONAL_12);
        ALL_LEFT_DIAGONALS.put(41, LEFT_DIAGONAL_12);
        ALL_LEFT_DIAGONALS.put(50, LEFT_DIAGONAL_12);
        ALL_LEFT_DIAGONALS.put(59, LEFT_DIAGONAL_12);
        ALL_LEFT_DIAGONALS.put(40, LEFT_DIAGONAL_13);
        ALL_LEFT_DIAGONALS.put(49, LEFT_DIAGONAL_13);
        ALL_LEFT_DIAGONALS.put(58, LEFT_DIAGONAL_13);
        ALL_LEFT_DIAGONALS.put(48, LEFT_DIAGONAL_14);
        ALL_LEFT_DIAGONALS.put(57, LEFT_DIAGONAL_14);
        ALL_LEFT_DIAGONALS.put(56, LEFT_DIAGONAL_15);
        ALL_LEFT_DIAGONALS = Collections.unmodifiableMap(ALL_LEFT_DIAGONALS);
    }

    private static void initializeRightDiagonals() {
        RIGHT_DIAGONAL_1.set(0);
        RIGHT_DIAGONAL_2.set(1);
        RIGHT_DIAGONAL_2.set(8);
        RIGHT_DIAGONAL_3.set(2);
        RIGHT_DIAGONAL_3.set(9);
        RIGHT_DIAGONAL_3.set(16);
        RIGHT_DIAGONAL_4.set(3);
        RIGHT_DIAGONAL_4.set(10);
        RIGHT_DIAGONAL_4.set(17);
        RIGHT_DIAGONAL_4.set(24);
        RIGHT_DIAGONAL_5.set(4);
        RIGHT_DIAGONAL_5.set(11);
        RIGHT_DIAGONAL_5.set(18);
        RIGHT_DIAGONAL_5.set(25);
        RIGHT_DIAGONAL_5.set(32);
        RIGHT_DIAGONAL_6.set(5);
        RIGHT_DIAGONAL_6.set(12);
        RIGHT_DIAGONAL_6.set(19);
        RIGHT_DIAGONAL_6.set(26);
        RIGHT_DIAGONAL_6.set(33);
        RIGHT_DIAGONAL_6.set(40);
        RIGHT_DIAGONAL_7.set(6);
        RIGHT_DIAGONAL_7.set(13);
        RIGHT_DIAGONAL_7.set(20);
        RIGHT_DIAGONAL_7.set(27);
        RIGHT_DIAGONAL_7.set(34);
        RIGHT_DIAGONAL_7.set(41);
        RIGHT_DIAGONAL_7.set(48);
        RIGHT_DIAGONAL_8.set(7);
        RIGHT_DIAGONAL_8.set(14);
        RIGHT_DIAGONAL_8.set(21);
        RIGHT_DIAGONAL_8.set(28);
        RIGHT_DIAGONAL_8.set(35);
        RIGHT_DIAGONAL_8.set(42);
        RIGHT_DIAGONAL_8.set(49);
        RIGHT_DIAGONAL_8.set(56);
        RIGHT_DIAGONAL_9.set(15);
        RIGHT_DIAGONAL_9.set(22);
        RIGHT_DIAGONAL_9.set(29);
        RIGHT_DIAGONAL_9.set(36);
        RIGHT_DIAGONAL_9.set(43);
        RIGHT_DIAGONAL_9.set(50);
        RIGHT_DIAGONAL_9.set(57);
        RIGHT_DIAGONAL_10.set(23);
        RIGHT_DIAGONAL_10.set(30);
        RIGHT_DIAGONAL_10.set(37);
        RIGHT_DIAGONAL_10.set(44);
        RIGHT_DIAGONAL_10.set(51);
        RIGHT_DIAGONAL_10.set(58);
        RIGHT_DIAGONAL_11.set(31);
        RIGHT_DIAGONAL_11.set(38);
        RIGHT_DIAGONAL_11.set(45);
        RIGHT_DIAGONAL_11.set(52);
        RIGHT_DIAGONAL_11.set(59);
        RIGHT_DIAGONAL_12.set(39);
        RIGHT_DIAGONAL_12.set(46);
        RIGHT_DIAGONAL_12.set(53);
        RIGHT_DIAGONAL_12.set(60);
        RIGHT_DIAGONAL_13.set(47);
        RIGHT_DIAGONAL_13.set(54);
        RIGHT_DIAGONAL_13.set(61);
        RIGHT_DIAGONAL_14.set(55);
        RIGHT_DIAGONAL_14.set(62);
        RIGHT_DIAGONAL_15.set(63);
        ALL_RIGHT_DIAGONALS.put(0, RIGHT_DIAGONAL_1);
        ALL_RIGHT_DIAGONALS.put(1, RIGHT_DIAGONAL_2);
        ALL_RIGHT_DIAGONALS.put(8, RIGHT_DIAGONAL_2);
        ALL_RIGHT_DIAGONALS.put(2, RIGHT_DIAGONAL_3);
        ALL_RIGHT_DIAGONALS.put(9, RIGHT_DIAGONAL_3);
        ALL_RIGHT_DIAGONALS.put(16, RIGHT_DIAGONAL_3);
        ALL_RIGHT_DIAGONALS.put(3, RIGHT_DIAGONAL_4);
        ALL_RIGHT_DIAGONALS.put(10, RIGHT_DIAGONAL_4);
        ALL_RIGHT_DIAGONALS.put(17, RIGHT_DIAGONAL_4);
        ALL_RIGHT_DIAGONALS.put(24, RIGHT_DIAGONAL_4);
        ALL_RIGHT_DIAGONALS.put(4, RIGHT_DIAGONAL_5);
        ALL_RIGHT_DIAGONALS.put(11, RIGHT_DIAGONAL_5);
        ALL_RIGHT_DIAGONALS.put(18, RIGHT_DIAGONAL_5);
        ALL_RIGHT_DIAGONALS.put(25, RIGHT_DIAGONAL_5);
        ALL_RIGHT_DIAGONALS.put(32, RIGHT_DIAGONAL_5);
        ALL_RIGHT_DIAGONALS.put(5, RIGHT_DIAGONAL_6);
        ALL_RIGHT_DIAGONALS.put(12, RIGHT_DIAGONAL_6);
        ALL_RIGHT_DIAGONALS.put(19, RIGHT_DIAGONAL_6);
        ALL_RIGHT_DIAGONALS.put(26, RIGHT_DIAGONAL_6);
        ALL_RIGHT_DIAGONALS.put(33, RIGHT_DIAGONAL_6);
        ALL_RIGHT_DIAGONALS.put(40, RIGHT_DIAGONAL_6);
        ALL_RIGHT_DIAGONALS.put(6, RIGHT_DIAGONAL_7);
        ALL_RIGHT_DIAGONALS.put(13, RIGHT_DIAGONAL_7);
        ALL_RIGHT_DIAGONALS.put(20, RIGHT_DIAGONAL_7);
        ALL_RIGHT_DIAGONALS.put(27, RIGHT_DIAGONAL_7);
        ALL_RIGHT_DIAGONALS.put(34, RIGHT_DIAGONAL_7);
        ALL_RIGHT_DIAGONALS.put(41, RIGHT_DIAGONAL_7);
        ALL_RIGHT_DIAGONALS.put(48, RIGHT_DIAGONAL_7);
        ALL_RIGHT_DIAGONALS.put(7, RIGHT_DIAGONAL_8);
        ALL_RIGHT_DIAGONALS.put(14, RIGHT_DIAGONAL_8);
        ALL_RIGHT_DIAGONALS.put(21, RIGHT_DIAGONAL_8);
        ALL_RIGHT_DIAGONALS.put(28, RIGHT_DIAGONAL_8);
        ALL_RIGHT_DIAGONALS.put(35, RIGHT_DIAGONAL_8);
        ALL_RIGHT_DIAGONALS.put(42, RIGHT_DIAGONAL_8);
        ALL_RIGHT_DIAGONALS.put(49, RIGHT_DIAGONAL_8);
        ALL_RIGHT_DIAGONALS.put(56, RIGHT_DIAGONAL_8);
        ALL_RIGHT_DIAGONALS.put(15, RIGHT_DIAGONAL_9);
        ALL_RIGHT_DIAGONALS.put(22, RIGHT_DIAGONAL_9);
        ALL_RIGHT_DIAGONALS.put(29, RIGHT_DIAGONAL_9);
        ALL_RIGHT_DIAGONALS.put(36, RIGHT_DIAGONAL_9);
        ALL_RIGHT_DIAGONALS.put(43, RIGHT_DIAGONAL_9);
        ALL_RIGHT_DIAGONALS.put(50, RIGHT_DIAGONAL_9);
        ALL_RIGHT_DIAGONALS.put(57, RIGHT_DIAGONAL_9);
        ALL_RIGHT_DIAGONALS.put(23, RIGHT_DIAGONAL_10);
        ALL_RIGHT_DIAGONALS.put(30, RIGHT_DIAGONAL_10);
        ALL_RIGHT_DIAGONALS.put(37, RIGHT_DIAGONAL_10);
        ALL_RIGHT_DIAGONALS.put(44, RIGHT_DIAGONAL_10);
        ALL_RIGHT_DIAGONALS.put(51, RIGHT_DIAGONAL_10);
        ALL_RIGHT_DIAGONALS.put(58, RIGHT_DIAGONAL_10);
        ALL_RIGHT_DIAGONALS.put(31, RIGHT_DIAGONAL_11);
        ALL_RIGHT_DIAGONALS.put(38, RIGHT_DIAGONAL_11);
        ALL_RIGHT_DIAGONALS.put(45, RIGHT_DIAGONAL_11);
        ALL_RIGHT_DIAGONALS.put(52, RIGHT_DIAGONAL_11);
        ALL_RIGHT_DIAGONALS.put(59, RIGHT_DIAGONAL_11);
        ALL_RIGHT_DIAGONALS.put(39, RIGHT_DIAGONAL_12);
        ALL_RIGHT_DIAGONALS.put(46, RIGHT_DIAGONAL_12);
        ALL_RIGHT_DIAGONALS.put(53, RIGHT_DIAGONAL_12);
        ALL_RIGHT_DIAGONALS.put(60, RIGHT_DIAGONAL_12);
        ALL_RIGHT_DIAGONALS.put(47, RIGHT_DIAGONAL_13);
        ALL_RIGHT_DIAGONALS.put(54, RIGHT_DIAGONAL_13);
        ALL_RIGHT_DIAGONALS.put(61, RIGHT_DIAGONAL_13);
        ALL_RIGHT_DIAGONALS.put(55, RIGHT_DIAGONAL_14);
        ALL_RIGHT_DIAGONALS.put(62, RIGHT_DIAGONAL_14);
        ALL_RIGHT_DIAGONALS.put(63, RIGHT_DIAGONAL_15);
        ALL_RIGHT_DIAGONALS = Collections.unmodifiableMap(ALL_RIGHT_DIAGONALS);
    }
}
