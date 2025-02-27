package nl.wesselbarten.tictactoe

import java.lang.Thread.sleep
import kotlin.random.Random

fun main() {
    val game = Game()

    println("Game Board Creation...")
    sleep(1000)
    print(game.printBoard())
    sleep(500)
    println()
    println("Board Created.")
    sleep(500)
    println("The game will start with player ${game.currentPlayer}")
    println()
    sleep(500)

    val availablePositions = Position.entries.toMutableList()
    var winningPlayer = Player.None
    while (availablePositions.size > 0) {
        println("Player ${game.currentPlayer}:")
        sleep(500)
        val index = Random.nextInt(until = availablePositions.size)
        val position = availablePositions[index]
        game.mark(position)
        print(game.printBoard())
        println()

        availablePositions.remove(position)
        winningPlayer = game.getWinningPlayer()
        if (winningPlayer != Player.None) {
            break
        }
        sleep(1000)
    }

    if (winningPlayer == Player.None) {
        println("THE GAME ENDS WITH A DRAW!")
        return
    }
    println("PLAYER ${game.getWinningPlayer()} WON!")
}

class Game {

    private val board = Board()

    var currentPlayer = Player.X
        private set

    fun getPlayerAt(position: Position): Player {
        return board.getPlayerAt(position)
    }

    fun mark(position: Position) {
        board.mark(position, currentPlayer)
        currentPlayer = Player.nextPlayer(currentPlayer)
    }

    fun getWinningPlayer(): Player {
        return board.getWinningPLayer()
    }

    fun printBoard(): String {
        return board.print()
    }
}

enum class Player {
    X,
    O,
    None;

    companion object {
        fun nextPlayer(currentPlayer: Player): Player {
            return when (currentPlayer) {
                X -> O
                O -> X
                else -> throw IllegalStateException("Cannot get the next player when there is no player selected.")
            }
        }
    }

    fun printNameForBoard(): String {
        return when (this) {
            X, O -> name
            None -> " "
        }
    }
}

class Board {

    private val positions = mutableMapOf<Position, Player?>()

    private val winningPatterns = listOf(
        // Horizontal
        WinningPattern(Position.TOP_LEFT, Position.TOP_CENTER, Position.TOP_RIGHT),
        WinningPattern(Position.MIDDLE_LEFT, Position.MIDDLE_CENTER, Position.MIDDLE_RIGHT),
        WinningPattern(Position.BOTTOM_LEFT, Position.BOTTOM_CENTER, Position.BOTTOM_RIGHT),
        // Vertical
        WinningPattern(Position.TOP_LEFT, Position.MIDDLE_LEFT, Position.BOTTOM_LEFT),
        WinningPattern(Position.TOP_CENTER, Position.MIDDLE_CENTER, Position.BOTTOM_CENTER),
        WinningPattern(Position.TOP_RIGHT, Position.MIDDLE_RIGHT, Position.BOTTOM_RIGHT),
        // Diagonal
        WinningPattern(Position.TOP_LEFT, Position.MIDDLE_CENTER, Position.BOTTOM_RIGHT),
        WinningPattern(Position.TOP_RIGHT, Position.MIDDLE_CENTER, Position.BOTTOM_LEFT),
    )

    fun getPlayerAt(position: Position): Player {
        return positions[position] ?: Player.None
    }

    fun mark(position: Position, player: Player) {
        positions.putIfAbsent(position, player)
    }

    fun getWinningPLayer(): Player {
        val winningPattern = winningPatterns.find { isSamePlayer(it.firstPosition, it.secondPosition, it.thirdPosition) }
        if (winningPattern != null) {
            return getPlayerAt(winningPattern.firstPosition)
        }

        return Player.None
    }

    private fun isSamePlayer(firstPosition: Position, secondPosition: Position, thirdPosition: Position): Boolean {
        return getPlayerAt(firstPosition) == getPlayerAt(secondPosition) &&
            getPlayerAt(secondPosition) == getPlayerAt(thirdPosition)
    }

    fun print(): String {
        return printBoardRow(Position.TOP_LEFT, Position.TOP_CENTER, Position.TOP_RIGHT) +
                printDividerRow() +
                printBoardRow(Position.MIDDLE_LEFT, Position.MIDDLE_CENTER, Position.MIDDLE_RIGHT) +
                printDividerRow() +
                printBoardRow(Position.BOTTOM_LEFT, Position.BOTTOM_CENTER, Position.BOTTOM_RIGHT)
    }

    private fun printBoardRow(firstPosition: Position, secondPosition: Position, thirdPosition: Position): String {
        return getPlayerAt(firstPosition).printNameForBoard() + "|" +
                getPlayerAt(secondPosition).printNameForBoard() + "|" +
                getPlayerAt(thirdPosition).printNameForBoard() + "\n"
    }

    private fun printDividerRow(): String {
        return "-+-+-" + "\n"
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

class WinningPattern(
    val firstPosition: Position,
    val secondPosition: Position,
    val thirdPosition: Position
)