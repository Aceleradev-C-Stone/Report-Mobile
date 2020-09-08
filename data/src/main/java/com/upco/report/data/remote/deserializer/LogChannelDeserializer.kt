package com.upco.report.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.upco.report.domain.enums.LogChannel
import java.lang.reflect.Type

class LogChannelDeserializer: JsonDeserializer<LogChannel> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LogChannel {
        val index = json.asInt
        return LogChannel.values()[index]
    }
}