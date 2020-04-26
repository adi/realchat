package com.sqreept.realchat

import io.javalin.Javalin
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun runServer() {
    logger.debug { "Starting server..." }
    val app = Javalin.create().start("0.0.0.0",3000)
    app.get("/") { ctx ->
        logger.warn { "Route / called" }
        ctx.result("Hello World!")
    }
    app.post("/register") { ctx ->
        ctx.result("{ \"error\": null }")
    }
}
