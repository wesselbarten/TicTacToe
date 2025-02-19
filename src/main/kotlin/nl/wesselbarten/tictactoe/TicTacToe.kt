package nl.wesselbarten.tictactoe

fun main() {

}

class Game {

    var currentPlayer = Player.X
        private set

    fun play() {
        if (currentPlayer == Player.X) {
            currentPlayer = Player.O
        }
    }
}

enum class Player {
    X, O;
}