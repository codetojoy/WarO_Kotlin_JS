
package net.codetojoy.waro.casino

class Collections() {
    fun shuffle(list: MutableList<Int>): MutableList<Int> {
        return list;
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
