package com.upco.report.data.remote.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.upco.report.domain.enums.UserRole
import java.lang.reflect.Type

class UserRoleDeserializer: JsonDeserializer<UserRole> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): UserRole {
        val index = json.asInt
        return UserRole.values()[index]
    }
}