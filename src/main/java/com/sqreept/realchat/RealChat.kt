package com.sqreept.realchat

import io.javalin.Javalin
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {
    logger.debug { "Starting server..." }
    val app = Javalin.create().start(3000)
    app.get("/") { ctx ->
        logger.warn { "Route / called" }
        ctx.result("Hello World!")
    }
}
