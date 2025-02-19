package nl.wesselbarten.tictactoe

fun main() {

}

class Game {

    val board = Board()

    var currentPlayer = Player.X
        private set

    fun play(position: Position) {
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

    val positions = mapOf<Position, Player?>()
}

enum class Position {
    TOP_LEFT
}