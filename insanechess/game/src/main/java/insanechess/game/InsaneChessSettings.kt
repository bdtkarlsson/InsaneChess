package insanechess.game

data class InsaneChessSettings(var whiteAIOn: Boolean = false,
                               var blackAIOn: Boolean = true,
                               var whiteAIDepth: Int = 3,
                               var blackAIDepth: Int = 3,
                               var maxMovesWithoutAttack: Int = 50)