
package net.codetojoy.waro.domain

data class Table(var players: List<Player>, var kitty: Hand) {
    fun assertTotals() {
        val playerTotal = players.sumBy { p -> p.playerStats.total } 
        val roundsTotal = players.sumBy { p -> p.playerStats.numRoundsWon } 
                
        if (kitty.cards.sum() != playerTotal) {
            throw Exception("internal error on player total")
        }

        if (kitty.cards.size != roundsTotal) {
            throw Exception("internal error on rounds total")
        }
    }
}
