package com.skillplugins.bot.command.commands

import com.skillplugins.bot.command.BotCommand
import com.skillplugins.bot.utils.MessageUtils
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object SendMessageCommand : BotCommand {

    override val commandName: String
        get() = "sendmessage"

    override fun onCommand(event: MessageReceivedEvent, args: MutableList<String>) {
        if (args.size != 0) {
            MessageUtils.sendMessage(event.textChannel, args.joinToString(" "))
        }
    }

}