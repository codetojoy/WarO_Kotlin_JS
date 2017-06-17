
package net.codetojoy.waro

import net.codetojoy.waro.domain.Player
import net.codetojoy.waro.strategy.*

object Config {
    var numGames = 0 
    var numCards = 0 
    var players: MutableList<Player>

    val MAX_CARD: String = "MAXCARD"
    val MIN_CARD: String = "MINCARD"
    val POP_CARD: String = "POPCARD"

    val isVerbose = true
   
    init {
        players = mutableListOf() 
    }

    fun buildStrategy(strategyStr: String) = when(strategyStr.toUpperCase()) {
        MAX_CARD -> MaxCard()
        MIN_CARD -> MinCard()
        POP_CARD -> PopCard()
        else -> throw IllegalStateException("unknown strategy: " + strategyStr)
    }

    fun buildPlayer(name: String, strategyStr: String, maxCard: Int): Player {
        val thisStrategy = buildStrategy(strategyStr) 

        return Player(name, thisStrategy, maxCard)
    }

    fun build() {
        this.numGames = 1
        this.numCards = 12
        
        val maxCard = this.numCards
        val players: MutableList<Player> = mutableListOf()
        players.add(buildPlayer("Beethoven", POP_CARD, maxCard))
        players.add(buildPlayer("Chopin", POP_CARD, maxCard))
        players.add(buildPlayer("Mozart", POP_CARD, maxCard))

        this.players = players;
    }
}
