package com.skillplugins.bot.command.commands

import com.skillplugins.bot.command.BotCommand
import com.skillplugins.bot.logger.BotLogger
import com.skillplugins.bot.misc.Constants
import com.skillplugins.bot.utils.MessageUtils
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

object PurgeCommand : BotCommand {

    override val commandName: String
        get() = "purge"

    override fun onCommand(event: MessageReceivedEvent, args: MutableList<String>) {
        val textChannel = event.textChannel
        val position = textChannel.position

        BotLogger.log {
            MessageUtils.sendMessage(it,
                "${event.author.name} purged a channel: #${textChannel.name}",
                Color.YELLOW)
        }

        textChannel.createCopy().queue { copiedChannel: TextChannel? ->
            textChannel.delete().queue {
                if (copiedChannel != null) {
                    event.guild.modifyTextChannelPositions().selectPosition(copiedChannel.position)
                        .moveTo(position).queue()
                    MessageUtils.sendMessage(copiedChannel, Constants.CHANNEL_PURGED, Color.YELLOW)
                }
            }
        }
    }
}