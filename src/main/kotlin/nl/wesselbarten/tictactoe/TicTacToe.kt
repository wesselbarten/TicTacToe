package nl.wesselbarten.tictactoe

fun main() {

}

class Game {

    val board = Board()

    var currentPlayer = Player.X
        private set

    fun play(position: Position) {
        board.positions[position] = currentPlayer
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

    val positions = mutableMapOf<Position, Player?>(
        Position.TOP_LEFT to null
    )
}

enum class Position {
    TOP_LEFT,
    TOP_RIGHT,
}