package com.sqreept.realchat

import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun runClient() {
    GlobalScope.launch {
        runClientAsync()
    }
    Thread.sleep(10000)
}

suspend fun runClientAsync() {
    logger.debug { "Register client..." }
    val channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build()

    val chatService = RealChatServiceGrpc.newStub(channel)

    val response = chatService.register(
        RegisterUserRequest.newBuilder()
            .setUserPublicKey("dsffgreg")
            .build())

    println(response)
}
