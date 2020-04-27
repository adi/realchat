package com.sqreept.realchat

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class RealChatCommand: CliktCommand(name = "realchat") {
    override fun run() = Unit
}

class Client: CliktCommand(help="Run the RealChat client") {
    override fun run() {
        runClient()
    }
}

class Server: CliktCommand(help="Run the RealChat server") {
    override fun run() {
        runServer()
    }
}

fun main(args: Array<String>) = RealChatCommand()
    .subcommands(Client(), Server())
    .main(args)
