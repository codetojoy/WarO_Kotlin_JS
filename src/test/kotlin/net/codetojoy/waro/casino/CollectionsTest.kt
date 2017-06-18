
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

import kotlin.test.*
import org.junit.*

class CollectionsTestSource {
    val collections = Collections()

    @Test fun testPickRandom() {
        val n = 3

        // test
        val pick = collections.pickRandom(n)

        assertTrue(pick >= 0)
        assertTrue(pick <= n)
    }

    @Test fun testShuffle() {
        val cards = mutableListOf(1,2,3,4,5,6,7,8,9,10)

        // test
        val result = collections.shuffle(cards)

        assertEquals(10, result.size)
        val uniques: MutableSet<Int> = mutableSetOf()
        result.forEach { uniques.add(it) }
        assertEquals(10, uniques.size)
    }
}
