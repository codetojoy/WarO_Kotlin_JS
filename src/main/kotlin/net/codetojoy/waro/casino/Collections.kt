
package net.codetojoy.waro.casino

import kotlin.js.Math

class Collections() {
    fun pickRandom(n: Int): Int = Math.floor(Math.random() * n)

    // http://stackoverflow.com/a/2450976/12704
    fun shuffle(list: MutableList<Int>): MutableList<Int> {
        var currentIndex = list.size

        while (currentIndex != 0) {
            // Pick a remaining element...
            val randomIndex = pickRandom(currentIndex)
            currentIndex -= 1

            // And swap it with the current element.
            val temporaryValue = list[currentIndex]
            list[currentIndex] = list[randomIndex]
            list[randomIndex] = temporaryValue
        }

        return list
    }

    fun partition(bigList: MutableList<Int>, n: Int): MutableList<MutableList<Int>> {
        var lists: MutableList<MutableList<Int>> = mutableListOf()

        val numItems = bigList.size
        val numItemsPerList = numItems / n
        var thisList: MutableList<Int> = mutableListOf() 

        for (i in 1..numItems) {
            thisList.add(bigList[i-1])

            val isLast = (i == numItems)
            val isBoundary = (i % numItemsPerList == 0)
            val isReset = (isBoundary || isLast)

            if (isReset) {
                lists.add(thisList)
                thisList = mutableListOf() 
            } 
        }

        return lists
    }
}
