
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.Log

class Dealer() {
    private val collections = Collections()
    private val log = Log

    fun deal(numCards: Int, players: List<Player>): Table {
        val numPlayers = players.size

        val hands = dealHands(numCards, numPlayers)

        val kitty = hands[0]

        for (index in 1..numPlayers) {
            players[index - 1].hand = hands[index].toMutableList()
        }

        val table = Table(players, kitty)

        return table
    }

    fun play(table: Table) =
        table.kitty.forEach { playRound(it, table.players) }

    // ------ internal

    internal fun playRound(prizeCard: Int, players: List<Player>): Player {
        val (winner, winningBid) = findRoundWinner(prizeCard, players)

        log.logBanner()
        log.log("Round Summary:")
        log.log("${winner.name} wins ${prizeCard} with ${winningBid.offer}")

        winner.playerStats.numRoundsWon++
        winner.playerStats.total += prizeCard

        return winner
    }

    internal fun findRoundWinner(prizeCard: Int, players: List<Player>): Pair<Player, Bid> {
        val fakePlayer = Player.fakePlayer()
        val fakeBid = Bid(-1, fakePlayer)
        val seed = Pair(fakePlayer, fakeBid)

        val result: Pair<Player,Bid> = players.fold(seed) { leader, player ->
            val (_, highBid: Bid) = leader
            val bid = player.getBid(prizeCard)

            if (bid.offer > highBid.offer) {
                Pair(bid.player, bid)
            } else {
                leader
            }
        }

        return result
    }

    internal fun dealHands(numCards: Int, numPlayers: Int): List<List<Int>> {
        var result = ArrayList<List<Int>>()

        val deck = newDeck(numCards)
        val numHands = numPlayers + 1

        collections.partition(deck, numHands).forEach { hand ->
            result.add(hand)
        }

        return result
    }

    internal fun newDeck(numCards: Int): MutableList<Int> {
        val deck = MutableList(numCards, { it + 1 })
        collections.shuffle(deck)
        return deck
    }
}