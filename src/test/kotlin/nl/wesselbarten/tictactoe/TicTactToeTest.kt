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

            game.play()

            assertEquals(Player.O, game.currentPlayer)
        }

        @Test
        fun `After player O has played, player X can play again`() {
            val game = Game()

            game.play()
            game.play()

            assertEquals(Player.X, game.currentPlayer)
        }
    }
}