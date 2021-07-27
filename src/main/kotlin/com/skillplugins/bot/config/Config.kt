package com.skillplugins.bot.config

import com.skillplugins.bot.config.api.JsonConfig

data class Config(
    var token: String = "YOUR TOKEN",
    var suggestionChannels: MutableList<String> = mutableListOf(),
    var logChannels: MutableList<String> = mutableListOf(),
    var fileUploadChannels: MutableList<String> = mutableListOf(),
    var suggestionEmojis: List<Long> = listOf(
        801405516365430834L,
        800133005657374720L
    ),
    var trustedURLs: List<String> = listOf()
) : JsonConfig("configs/config.json")