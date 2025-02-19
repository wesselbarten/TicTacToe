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
        positions[position] = player
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