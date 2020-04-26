package com.sqreept.realchat

import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.rx.rxResponse
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun runClient() {
    logger.debug { "Register client..." }
    "http://localhost:3000/register".httpPost().rxResponse().subscribe { response ->
        logger.debug { "Server responded ${response.toString()}" }
    }
    Thread.sleep(10000)
}
