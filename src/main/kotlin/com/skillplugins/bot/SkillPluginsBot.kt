package com.skillplugins.bot

import com.skillplugins.bot.config.Config
import com.skillplugins.bot.config.ConfigLoader
import com.skillplugins.bot.listeners.MessageListener
import com.skillplugins.bot.misc.Constants
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

object SkillPluginsBot {

    val LOGGER: Logger? = LoggerFactory.getLogger(SkillPluginsBot.javaClass)

    var shardManager: ShardManager? = null

    private var initialized = false

    fun init() {
        if (initialized) return
        initialized = true

        Runtime.getRuntime().addShutdownHook(Thread {
            shardManager?.setStatus(OnlineStatus.OFFLINE)
            shardManager?.shutdown()
        })

        val gatewayIntents = listOf<GatewayIntent>(
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.DIRECT_MESSAGES,
            GatewayIntent.GUILD_BANS,
            GatewayIntent.GUILD_MESSAGE_REACTIONS,
            GatewayIntent.GUILD_EMOJIS
        )

        val config: Config? = ConfigLoader.config

        if (config == null) {
            LOGGER?.warn("Failed to initialize config")
            return
        }

        DefaultShardManagerBuilder.create(gatewayIntents)
            .setToken(config.token)
            .setActivity(Constants.ACTIVITY)
            .setStatus(Constants.ONLINE_STATUS)
            .addEventListeners(
                MessageListener
            ).apply { shardManager = build() }

    }

}
