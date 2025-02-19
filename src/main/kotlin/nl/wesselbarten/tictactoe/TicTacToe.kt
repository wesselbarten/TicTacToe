package nl.wesselbarten.tictactoe

fun main() {

}

class Game {

    val board = Board()

    var currentPlayer = Player.X
        private set

    fun mark(position: Position) {
        board.mark(position, currentPlayer)
        currentPlayer = Player.nextPlayer(currentPlayer)
    }

    fun getWinningPlayer(): Player? {
        return board.getWinningPLayer()
    }
}

enum class Player {
    X, O;

    companion object {
        fun nextPlayer(currentPlayer: Player): Player {
            return when (currentPlayer) {
                X -> O
                O -> X
            }
        }
    }
}

class Board {

    private val positions = mutableMapOf<Position, Player?>()

    private val winningPatterns = listOf(
        WinningPattern(Position.TOP_LEFT, Position.TOP_CENTER, Position.TOP_RIGHT),
        WinningPattern(Position.MIDDLE_LEFT, Position.MIDDLE_CENTER, Position.MIDDLE_RIGHT),
        WinningPattern(Position.TOP_LEFT, Position.MIDDLE_CENTER, Position.BOTTOM_RIGHT),
    )

    fun getPlayerAt(position: Position): Player? {
        return positions[position]
    }

    fun mark(position: Position, player: Player) {
        positions.putIfAbsent(position, player)
    }

    fun getWinningPLayer(): Player? {
        val winningPattern = winningPatterns.find { isSamePlayer(it.firstPosition, it.secondPosition, it.thirdPosition) }
        if (winningPattern != null) {
            return getPlayerAt(winningPattern.firstPosition)
        }

        return null
    }

    private fun isSamePlayer(firstPosition: Position, secondPosition: Position, thirdPosition: Position): Boolean {
        return getPlayerAt(firstPosition) == getPlayerAt(secondPosition) &&
            getPlayerAt(secondPosition) == getPlayerAt(thirdPosition)
    }
}

enum class Position {
    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT,
    MIDDLE_LEFT,
    MIDDLE_CENTER,
    MIDDLE_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT;
}

class WinningPattern(val firstPosition: Position, val secondPosition: Position, val thirdPosition: Position)