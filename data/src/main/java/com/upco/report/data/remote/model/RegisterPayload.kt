package com.upco.report.data.remote.model

import com.google.gson.annotations.SerializedName

data class RegisterPayload(
    @SerializedName("data") val user: UserPayload
)