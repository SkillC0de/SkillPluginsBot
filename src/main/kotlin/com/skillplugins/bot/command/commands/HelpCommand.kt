package com.skillplugins.bot.command.commands

import com.skillplugins.bot.command.BotCommand
import com.skillplugins.bot.command.CommandManager
import com.skillplugins.bot.misc.Constants
import com.skillplugins.bot.utils.MessageUtils
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object HelpCommand : BotCommand {

    override val adminCommand: Boolean
        get() = false

    override val commandName: String
        get() = "help"

    override fun onCommand(event: MessageReceivedEvent, args: MutableList<String>) {
        val playerCommandList = CommandManager.commands.values.filter { !it.adminCommand }.map { it.commandName }
        val adminCommandList = CommandManager.commands.values.filter { it.adminCommand }.map { it.commandName }

        val playerCommands = playerCommandList.joinToString { "${Constants.COMMAND_PREFIX + it}\n" }
        val adminCommands = adminCommandList.joinToString { "${Constants.COMMAND_PREFIX + it}\n" }

        MessageUtils.sendMessage(event.textChannel,
            String.format(Constants.HELP_MESSAGE, playerCommands, adminCommands).replace(",", ""))
    }
}