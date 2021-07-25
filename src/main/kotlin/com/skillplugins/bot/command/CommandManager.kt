package com.skillplugins.bot.command

import com.skillplugins.bot.command.commands.AddChannelCommand
import com.skillplugins.bot.command.commands.HelpCommand
import com.skillplugins.bot.command.commands.PurgeCommand
import com.skillplugins.bot.command.commands.SendMessageCommand
import com.skillplugins.bot.misc.Constants
import com.skillplugins.bot.utils.MessageUtils
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

object CommandManager {

    val commands = mutableMapOf<String, BotCommand>()

    init {
        commands[AddChannelCommand.commandName] = AddChannelCommand
        commands[HelpCommand.commandName] = HelpCommand
        commands[SendMessageCommand.commandName] = SendMessageCommand
        commands[PurgeCommand.commandName] = PurgeCommand
    }


    fun executeCommandIfExists(
        event: MessageReceivedEvent,
        commandName: String?,
        args: MutableList<String>,
    ): Boolean {
        if (commandName != null && commands.containsKey(commandName)) {
            commands[commandName]?.apply {
                if (!adminCommand
                    || event.member?.hasPermission(Permission.ADMINISTRATOR) == true
                ) {
                    onCommand(event, args)
                } else {
                    MessageUtils.sendMessage(event.textChannel, Constants.NO_PERMISSION, Color.RED)
                }
                return true
            }
        }
        return false
    }


}