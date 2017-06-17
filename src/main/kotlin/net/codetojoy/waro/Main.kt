
package net.codetojoy.waro

import net.codetojoy.waro.casino.Tourney

external fun require(module:String):dynamic

public fun main(args: Array<String>) {
    println("WarO Kotlin JS")

    val express = require("express")
    val app = express()
    app.use(express.static("public"))
    app.use(express.static("files"))

    app.get("/", { req, res ->
        var result = "unknown" 

        try {
            val config = Config
            config.build()

            val numCards = config.numCards
            val numGames = config.numGames
            val players = config.players

            val tourney = Tourney(players, numGames, numCards)

            tourney.playGames()

            result = Log.buffer
        } catch (e: Throwable) {
            console.log(e)
            result = "i am NOT such a beautiful butterfly"
        }

        res.type("text/plain")
        res.send(result)
    })

    app.listen(3000, {
        println("Listening on port 3000")
    })
}
