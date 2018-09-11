package insanechess.backend.constants;

import java.util.BitSet;

public class FullEmptySet {

	public static final BitSet EMPTY_SET = new BitSet();
	public static final BitSet FULL_SET = new BitSet();

	static {
		FULL_SET.set(0, 63);
		EMPTY_SET.clear();
	}
}
