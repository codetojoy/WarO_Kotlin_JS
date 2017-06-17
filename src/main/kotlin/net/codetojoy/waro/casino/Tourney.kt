
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.*

class Tourney(internal val players: List<Player>, 
              internal val numGames: Int, 
              internal val numCards: Int) {
    private val log = Log

    fun playGames() {
        log.log("Tourney WTF 2")

        for (i in 1..numGames) {
            playGame(numCards)
        }

        log.logBanner()
        log.log("Tourney summary:")

        players.forEach { p ->
            log.log("${p.name} has ${p.playerStats.numGamesWon} wins over ${numGames} games")
        }
    }

    // ------- internal

    internal fun playGame(numCards: Int) {
        val game = Game()

        game.playGame(numCards, players)

        players.forEach { it.clear() }
    }
}
