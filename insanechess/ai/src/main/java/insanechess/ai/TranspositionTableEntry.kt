package insanechess.ai

data class TranspositionTableEntry(val depth: Int, val value: Double, val flag: EntryFlag, val age: Long)
enum class EntryFlag { EXACT, LOWER, UPPER }






