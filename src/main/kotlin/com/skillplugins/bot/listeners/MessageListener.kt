package com.skillplugins.bot.listeners

import com.skillplugins.bot.SkillPluginsBot
import com.skillplugins.bot.command.CommandManager
import com.skillplugins.bot.config.Config
import com.skillplugins.bot.config.ConfigLoader
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

        if (config?.suggestionChannels?.contains(channel.name) == true) {
            config.suggestionEmojis.forEach {
                val emote: Emote? = SkillPluginsBot.shardManager?.getEmoteById(it)
                emote?.let { event.message.addReaction(emote).queue() }
            }
        }

        //command system
        val message: String = event.message.contentDisplay
        if (message.startsWith(Constants.COMMAND_PREFIX, true)) {
            val split = message.split(" ")
            val args = mutableListOf<String>()
            var commandName: String? = null
            split.map { it.replace(Constants.COMMAND_PREFIX, "") }
                .forEachIndexed { index, s ->
                    if (index == 0 && s.isNotEmpty()) {
                        commandName = s
                    } else {
                        args.add(s)
                    }
                }

            if (!CommandManager.executeCommandIfExists(event, commandName, args)) {
                MessageUtils.sendMessage(channel, Constants.UNKNOWN_COMMAND, Color.RED)
            }
        }
    }

}