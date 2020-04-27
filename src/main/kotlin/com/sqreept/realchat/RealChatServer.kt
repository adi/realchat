package com.sqreept.realchat

import io.grpc.ServerBuilder
import kotlinx.coroutines.asCoroutineDispatcher
import mu.KotlinLogging
import java.util.concurrent.Executors.newFixedThreadPool

private val logger = KotlinLogging.logger {}

class RealChatService: RealChatServiceImplBase(
    coroutineContext = newFixedThreadPool(4).asCoroutineDispatcher()
) {
    override suspend fun register(request: RegisterUserRequest): RegisterUserResponse {
        return RegisterUserResponse.newBuilder()
            .setId("dsuhklrgerjig")
            .setServerPublicKey("sdfgergetgthrerg")
            .build()
    }

    fun shutdown() {
        println("Shutting down server")
//        clientChannels.forEach { (client, channel) ->
//            println("Closing client channel $client")
//            channel.close()
//        }
//        clientChannels.clear()
    }
}

fun runServer() {
    logger.debug { "Starting server..." }
    val realChatService = RealChatService()
    val server = ServerBuilder
        .forPort(8080)
        .addService(realChatService)
        .build()

    Runtime.getRuntime().addShutdownHook(Thread {
        println("Ups, JVM shutdown")

        realChatService.shutdown()
        server.shutdown()
        server.awaitTermination()

        println("Chat service stopped")
    })

    server.start()
    println("Chat service started")
    server.awaitTermination()
}
