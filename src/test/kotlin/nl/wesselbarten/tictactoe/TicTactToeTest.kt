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
    }
}