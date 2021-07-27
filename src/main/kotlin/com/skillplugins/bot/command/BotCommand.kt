package com.skillplugins.bot.command

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

interface BotCommand {

    val commandName: String

    val adminCommand: Boolean
        get() = true

    val deleteMessage: Boolean
        get() = false


    fun onCommand(
        event: MessageReceivedEvent,
        args: MutableList<String>,
    )

}