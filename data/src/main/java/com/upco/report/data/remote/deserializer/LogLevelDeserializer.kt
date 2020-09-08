package com.upco.report.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.upco.report.domain.enums.LogLevel
import java.lang.reflect.Type

class LogLevelDeserializer: JsonDeserializer<LogLevel> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LogLevel {
        val index = json.asInt
        return LogLevel.values()[index]
    }
}