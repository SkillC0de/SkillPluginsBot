package com.skillplugins.bot.command.commands

import com.skillplugins.bot.command.BotCommand
import com.skillplugins.bot.config.ConfigLoader
import com.skillplugins.bot.misc.Constants
import com.skillplugins.bot.misc.ChannelType
import com.skillplugins.bot.utils.MessageUtils
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

object AddChannelCommand : BotCommand {

    override val commandName: String
        get() = "addchannel"

    override fun onCommand(event: MessageReceivedEvent, args: MutableList<String>) {

        if (args.size < 2) {
            MessageUtils.sendMessage(event.textChannel, Constants.ADD_CHANNEL_SYNTAX, Color.RED)
            return
        }

        val channelType = args[0].uppercase()
        val channelName = args[1].replace("#", "")

        if (!ChannelType.values().map { it.name }.contains(channelType)) {
            MessageUtils.sendMessage(event.textChannel, Constants.UNKNOWN_CHANNEL_TYPE, Color.RED)
            return
        }

        val textChannels = event.guild.getTextChannelsByName(channelName, true)
        if (textChannels.size == 0) {
            MessageUtils.sendMessage(event.textChannel, Constants.UNKNOWN_CHANNEL, Color.RED)
        } else {
            ChannelType.addListEntry(channelType, channelName)
            MessageUtils.sendMessage(event.textChannel, Constants.CHANNEL_ADDED, Color.YELLOW)
        }
    }


}