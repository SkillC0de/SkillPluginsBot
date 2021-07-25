package com.skillplugins.bot.config

import com.skillplugins.bot.config.Config
import com.skillplugins.bot.config.api.JsonConfigLoader

object ConfigLoader : JsonConfigLoader<Config>(Config())

