package nl.wesselbarten.tictactoe

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TicTactToeTest {

    @Nested
    inner class PlayerTest {

        @Test
        fun `Player X is the first player to play`() {
            val game = Game()

            assertEquals(Player.X, game.currentPlayer)
        }

        @Test
        fun `After player X has played, player O can play`() {
            val game = Game()

            game.play(Position.TOP_LEFT)

            assertEquals(Player.O, game.currentPlayer)
        }

        @Test
        fun `After player O has played, player X can play again`() {
            val game = Game()

            game.play(Position.TOP_LEFT)
            game.play(Position.TOP_LEFT)

            assertEquals(Player.X, game.currentPlayer)
        }
    }

    @Nested
    inner class BoardTest {

        @Test
        fun `Player X can mark the top left position on the board`() {
            val game = Game()

            game.play(Position.TOP_LEFT)

            val position = game.board.positions[Position.TOP_LEFT]

            assertEquals(Player.X, position)
        }

        @Test
        fun `Player X can mark the top right position on the board`() {
            val game = Game()

            game.play(Position.TOP_RIGHT)

            val position = game.board.positions[Position.TOP_RIGHT]

            assertEquals(Player.X, position)
        }
    }
}