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

            val position = game.getPlayerAt(Position.TOP_LEFT)

            assertEquals(Player.X, position)
        }

        @Test
        fun `Player X can mark the top right position on the board`() {
            val game = Game()

            game.mark(Position.TOP_RIGHT)

            val position = game.getPlayerAt(Position.TOP_RIGHT)

            assertEquals(Player.X, position)
        }

        @Test
        fun `After Player X has marked the top left position, Player O can mark the top right position`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)
            game.mark(Position.TOP_RIGHT)

            assertEquals(Player.X, game.getPlayerAt(Position.TOP_LEFT))
            assertEquals(Player.O, game.getPlayerAt(Position.TOP_RIGHT))
        }

        @Test
        fun `A position cannot be marked twice`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)
            game.mark(Position.TOP_LEFT)

            assertEquals(Player.X, game.getPlayerAt(Position.TOP_LEFT))
        }

        @Test
        fun `All positions can be marked`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)
            game.mark(Position.TOP_CENTER)
            game.mark(Position.TOP_RIGHT)
            game.mark(Position.MIDDLE_LEFT)
            game.mark(Position.MIDDLE_CENTER)
            game.mark(Position.MIDDLE_RIGHT)
            game.mark(Position.BOTTOM_LEFT)
            game.mark(Position.BOTTOM_CENTER)
            game.mark(Position.BOTTOM_RIGHT)

            assertEquals(Player.X, game.getPlayerAt(Position.TOP_LEFT))
            assertEquals(Player.O, game.getPlayerAt(Position.TOP_CENTER))
            assertEquals(Player.X, game.getPlayerAt(Position.TOP_RIGHT))
            assertEquals(Player.O, game.getPlayerAt(Position.MIDDLE_LEFT))
            assertEquals(Player.X, game.getPlayerAt(Position.MIDDLE_CENTER))
            assertEquals(Player.O, game.getPlayerAt(Position.MIDDLE_RIGHT))
            assertEquals(Player.X, game.getPlayerAt(Position.BOTTOM_LEFT))
            assertEquals(Player.O, game.getPlayerAt(Position.BOTTOM_CENTER))
            assertEquals(Player.X, game.getPlayerAt(Position.BOTTOM_RIGHT))
        }
    }

    @Nested
    inner class WinningPlayer {

        @Test
        fun `Player X wins when has marked the top left, top center and top right positions`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)
            game.mark(Position.MIDDLE_LEFT)
            game.mark(Position.TOP_CENTER)
            game.mark(Position.MIDDLE_CENTER)
            game.mark(Position.TOP_RIGHT)

            assertEquals(Player.X, game.getWinningPlayer())
        }

        @Test
        fun `Player X wins when has marked the middle left, middle center and middle right positions`() {
            val game = Game()

            game.mark(Position.MIDDLE_LEFT)
            game.mark(Position.TOP_LEFT)
            game.mark(Position.MIDDLE_CENTER)
            game.mark(Position.TOP_CENTER)
            game.mark(Position.MIDDLE_RIGHT)

            assertEquals(Player.X, game.getWinningPlayer())
        }

        @Test
        fun `Player X wins when has marked the bottom left, bottom center and bottom right positions`() {
            val game = Game()

            game.mark(Position.BOTTOM_LEFT)
            game.mark(Position.MIDDLE_LEFT)
            game.mark(Position.BOTTOM_CENTER)
            game.mark(Position.TOP_CENTER)
            game.mark(Position.BOTTOM_RIGHT)

            assertEquals(Player.X, game.getWinningPlayer())
        }

        @Test
        fun `Player X wins when has marked the top left, middle center and bottom right positions`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)
            game.mark(Position.MIDDLE_LEFT)
            game.mark(Position.MIDDLE_CENTER)
            game.mark(Position.TOP_CENTER)
            game.mark(Position.BOTTOM_RIGHT)

            assertEquals(Player.X, game.getWinningPlayer())
        }

        @Test
        fun `Player X wins when has marked the top right, middle right and bottom right positions`() {
            val game = Game()

            game.mark(Position.TOP_RIGHT)
            game.mark(Position.MIDDLE_LEFT)
            game.mark(Position.MIDDLE_RIGHT)
            game.mark(Position.TOP_CENTER)
            game.mark(Position.BOTTOM_RIGHT)

            assertEquals(Player.X, game.getWinningPlayer())
        }

        @Test
        fun `Player X does not win when has marked the top left, middle center and middle right positions`() {
            val game = Game()

            game.mark(Position.TOP_LEFT)
            game.mark(Position.MIDDLE_LEFT)
            game.mark(Position.MIDDLE_CENTER)
            game.mark(Position.TOP_CENTER)
            game.mark(Position.MIDDLE_RIGHT)

            assertEquals(Player.None, game.getWinningPlayer())
        }
    }
}