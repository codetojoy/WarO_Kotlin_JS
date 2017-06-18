
package net.codetojoy.waro.domain

class Hand(var cards: List<Int>) {
    fun take(card: Int) {
        cards = cards.filter { it != card }
    }

    fun clear() {
        cards = listOf()
    }

    override fun toString() = cards.toString() 
}
