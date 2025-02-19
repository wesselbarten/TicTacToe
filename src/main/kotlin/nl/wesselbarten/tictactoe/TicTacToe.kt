package nl.wesselbarten.tictactoe

fun main() {

}

class Game {

    var currentPlayer = Player.X
        private set

    fun play() {
        if (currentPlayer == Player.X) {
            currentPlayer = Player.O
            return
        }

        if (currentPlayer == Player.O) {
            currentPlayer = Player.X
        }
    }
}

enum class Player {
    X, O;
}