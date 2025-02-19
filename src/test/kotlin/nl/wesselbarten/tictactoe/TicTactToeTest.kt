package nl.wesselbarten.tictactoe

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TicTactToeTest {

    @Nested
    inner class PlayerTest {

        @Test
        fun `Player X is the first player to mark`() {
            val game = Game()

            assertEquals(Player.X, game.currentPlayer)
        }

        @Test
        fun `After player X has marked, player O can mark`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)

            assertEquals(Player.O, game.currentPlayer)
        }

        @Test
        fun `After player O has marked, player X can mark again`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)
            game.mark(Position.TOP_LEFT)

            assertEquals(Player.X, game.currentPlayer)
        }
    }

    @Nested
    inner class BoardTest {

        @Test
        fun `Player X can mark the top left position on the board`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)

            val position = game.board.getPlayerAt(Position.TOP_LEFT)

            assertEquals(Player.X, position)
        }

        @Test
        fun `Player X can mark the top right position on the board`() {
            val game = Game()

            game.mark(Position.TOP_RIGHT)

            val position = game.board.getPlayerAt(Position.TOP_RIGHT)

            assertEquals(Player.X, position)
        }

        @Test
        fun `After Player X has marked the top left position, Player O can mark the top right position`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)
            game.mark(Position.TOP_RIGHT)

            assertEquals(Player.X, game.board.getPlayerAt(Position.TOP_LEFT))
            assertEquals(Player.O, game.board.getPlayerAt(Position.TOP_RIGHT))
        }
    }
}