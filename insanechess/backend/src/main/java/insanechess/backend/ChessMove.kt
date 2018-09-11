package insanechess.backend

import java.util.*


data class ChessMove @JvmOverloads constructor(var from: Int, var to: Int, var value: Double = 0.0) {

    fun getBitSet(): BitSet {
        val b = BitSet()
        b.set(to)
        b.set(from)
        return b
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is ChessMove) return false
        return to == other.to && from == other.from
    }

    override fun hashCode(): Int {
        var result = to
        result = 31 * result + from
        result = 31 * result + value.hashCode()
        return result
    }
}