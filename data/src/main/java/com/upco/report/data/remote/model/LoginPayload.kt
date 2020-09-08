package com.upco.report.data.remote.model

import com.google.gson.annotations.SerializedName
import com.upco.report.domain.enums.UserRole
import java.util.*

class LoginPayload(
    @SerializedName("data") val loginPayload: UserTokenPayload
)

class UserTokenPayload(
    @SerializedName("user") val user: UserPayload,
    @SerializedName("token") val token: String,
    @SerializedName("expiresIn") val expiresIn: Int,
)

class UserPayload(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: UserRole,
    @SerializedName("createdAt") val createdAt: Date
)