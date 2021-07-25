package com.skillplugins.bot.logger

import com.skillplugins.bot.SkillPluginsBot
import com.skillplugins.bot.config.ConfigLoader
import net.dv8tion.jda.api.entities.TextChannel

object BotLogger {

    val channels: MutableList<TextChannel> = mutableListOf()

    init {
        ConfigLoader.config?.logChannels?.forEach { channelName: String ->
            SkillPluginsBot.shardManager?.textChannels?.forEach {
                if (it.name.trim() == channelName) {
                    channels.add(it)
                }
            }
        }
    }

    fun log(consumer: (TextChannel) -> Unit) {
        channels.filterNotNull().forEach(consumer::invoke)
    }

}