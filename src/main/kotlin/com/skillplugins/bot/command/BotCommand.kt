package com.skillplugins.bot.command

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

interface BotCommand {

    val commandName: String

    val adminCommand: Boolean
        get() = true


    fun onCommand(
        event: MessageReceivedEvent,
        args: MutableList<String>,
    )

}