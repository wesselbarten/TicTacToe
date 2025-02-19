package nl.wesselbarten.tictactoe

fun main() {

}

class Game {

    var currentPlayer = Player.X
        private set

    fun play() {
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