package com.skillplugins.bot.listeners

import com.skillplugins.bot.SkillPluginsBot
import com.skillplugins.bot.config.Config
import com.skillplugins.bot.config.ConfigLoader
import com.skillplugins.bot.logger.BotLogger
import com.skillplugins.bot.misc.Constants
import com.skillplugins.bot.utils.MessageUtils
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Emote
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color

object MessageListener : ListenerAdapter() {

    private val config: Config? = ConfigLoader.config

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.channel.type != ChannelType.TEXT) return
        val channel = event.textChannel
        val message = event.message.contentDisplay
        val args = message.split(" ")

        onSuggestionMessage(event, message, args)
        onLink(event, message, args)
    }

    private fun onSuggestionMessage(
        event: MessageReceivedEvent,
        message: String,
        args: List<String>,
    ) {
        if (config?.suggestionChannels?.contains(event.channel.name) == true) {
            config.suggestionEmojis.forEach {
                val emote: Emote? = SkillPluginsBot.shardManager?.getEmoteById(it)
                emote?.let { event.message.addReaction(emote).queue() }
            }
        }
    }

    private fun onLink(
        event: MessageReceivedEvent,
        message: String,
        args: List<String>,
    ) {
        args.forEach { arg ->
            if (arg.startsWith("http")
                || arg.startsWith("https")
            ) {
                config?.trustedURLs?.forEach {
                    if (arg.lowercase().contains(it.lowercase())) {
                        return
                    }
                }
                event.message.delete().queue {
                    MessageUtils.sendMessage(event.textChannel,
                        String.format(Constants.URL_POST, event.author.name),
                        Color.RED)

                    BotLogger.log {
                        MessageUtils.sendMessage(it,
                            String.format(Constants.URL_POST_LOG, event.author.name, arg), Color.YELLOW)
                    }
                }
            }
        }
    }
}