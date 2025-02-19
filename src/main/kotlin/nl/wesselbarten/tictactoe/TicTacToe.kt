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

    fun getPlayerAt(position: Position): Player? {
        return positions[position]
    }

    fun mark(position: Position, player: Player) {
        positions.putIfAbsent(position, player)
    }

    fun getWinningPLayer(): Player? {
        if (getPlayerAt(Position.TOP_LEFT) == getPlayerAt(Position.TOP_CENTER) &&
            getPlayerAt(Position.TOP_CENTER) == getPlayerAt(Position.TOP_RIGHT)) {
            return getPlayerAt(Position.TOP_LEFT)
        }

        return null
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