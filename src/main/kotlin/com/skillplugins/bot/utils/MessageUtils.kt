package com.skillplugins.bot.utils

import com.skillplugins.bot.misc.Constants
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.TextChannel
import java.awt.Color
import java.util.*

object MessageUtils {

    fun sendMessage(channel: TextChannel, message: String, color: Color = Constants.DEFAULT_COLOR) {

        val embedBuilder = EmbedBuilder()

        embedBuilder.setColor(color)
        embedBuilder.setDescription(message)
        embedBuilder.setThumbnail("https://cdn.discordapp.com/icons/556864477241409536/9f5c43bb73014444aef3c6d4cfff2755.png?size=4096")
        embedBuilder.setImage("https://cdn.discordapp.com/attachments/874741731940249610/874743031360815144/oie_VgZP6ezch1zD.gif")
        embedBuilder.setFooter("\uD83E\uDDC3 skillplugins.com")

        channel.sendMessage(embedBuilder.build()).queue()
    }

}