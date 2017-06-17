
package net.codetojoy.waro

object Log {
    var buffer = ""

    fun log(s: String) {
        println(s)
        buffer = buffer + s + "\n"
    }

    fun logBanner() {
        log("---------------------")
    }
}
