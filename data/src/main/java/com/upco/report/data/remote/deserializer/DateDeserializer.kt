package com.upco.report.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer: JsonDeserializer<Date> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        val format = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale("pt", "BR")
        )
        return format.parse(json.asString)!!
    }
}