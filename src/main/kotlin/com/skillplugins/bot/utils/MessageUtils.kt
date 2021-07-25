package com.skillplugins.bot.utils

import com.skillplugins.bot.misc.Constants
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import java.awt.Color

object MessageUtils {

    fun sendMessage(channel: TextChannel, message: String, color: Color = Constants.DEFAULT_COLOR) =
        channel.sendMessage(EmbedBuilder().setColor(color).setDescription(message).build()).queue()

}