package com.upco.report.data.remote.mapper

import com.upco.report.data.remote.model.UserPayload
import com.upco.report.domain.entities.User

object UserMapper {

    fun map(payload: UserPayload) = User(
        id = payload.id,
        name = payload.name,
        email = payload.email,
        role = payload.role,
        createdAt = payload.createdAt
    )
}