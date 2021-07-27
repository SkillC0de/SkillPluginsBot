package com.skillplugins.bot.misc

import com.skillplugins.bot.config.ConfigLoader

enum class ChannelType(val channelList: MutableList<String>?) {
    SUGGESTIONS(ConfigLoader.config?.suggestionChannels),
    LOG(ConfigLoader.config?.logChannels),
    FILE_UPLOAD(ConfigLoader.config?.fileUploadChannels);

    companion object {
        fun addListEntry(channelType: ChannelType, channel: String) {
            channelType.channelList?.add(channel)
            ConfigLoader.save(ConfigLoader.config)
        }

        fun addListEntry(channelType: String, channel: String) {
            if (values().map { it.name }.contains(channelType)) {
                addListEntry(valueOf(channelType), channel)
            }
        }
    }
}