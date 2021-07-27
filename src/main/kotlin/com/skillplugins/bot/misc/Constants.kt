package com.skillplugins.bot.misc

import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import java.awt.Color

object Constants {
    val ACTIVITY = Activity.listening("SkillCode")
    val ONLINE_STATUS = OnlineStatus.ONLINE
    val DEFAULT_COLOR: Color = Color.CYAN;

    /* Strings */
    const val COMMAND_PREFIX = "./"

    //messages
    const val UNKNOWN_COMMAND = "I don't know this command. "
    const val NO_PERMISSION = "You don't look like an admin to me."
    const val HELP_MESSAGE = "**Player Commands**\n\n%1\$s \n**Admin Commands**\n\n%2\$s"
    const val ADD_CHANNEL_SYNTAX = "${COMMAND_PREFIX}addchannel <channel type> <channel>"
    const val UNKNOWN_CHANNEL_TYPE = "Unknown channel type."
    const val UNKNOWN_CHANNEL = "Unknown channel."
    const val CHANNEL_ADDED = "The channel has been added."
    const val CHANNEL_PURGED = "This channel has been purged."
    const val URL_POST = "Please don't post URLs, %s"
    const val URL_POST_LOG = "%1\$s tried to post an URL: %2\$s"

}