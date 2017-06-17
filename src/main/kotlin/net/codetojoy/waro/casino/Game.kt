
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.*

class Game() {
    private val dealer = Dealer()
    private val log = Log

    fun playGame(numCards: Int, players: List<Player>): Player {
        val table = dealer.deal(numCards, players)

        return playGame(table)
    }

    fun playGame(table: Table): Player {
        if (Config.isVerbose) {
            log.log("kitty: ${table.kitty.toString()}")
            table.players.forEach { p ->
                log.log("${p.name}: ${p.hand.toString()}")
            }
        }

        dealer.play(table)

        return determineWinner(table)
    }

    // ---- internal

    internal fun determineWinner(table: Table): Player {
        val players = table.players

        table.assertTotals()

        val tmpWinner:Player? = players.maxBy { p -> p.playerStats.total }
        val winner:Player = tmpWinner!!

        if (Config.isVerbose) {
            players.forEach { p -> 
                val stats = p.playerStats
                log.log("${p.name} won ${stats.numRoundsWon} rounds with ${stats.total}")
            }
        }

        winner.playerStats.numGamesWon++

        log.logBanner()
        log.log("Game summary:")
        log.log("overall WINNER is: ${winner.name}")

        return winner
    }
}
