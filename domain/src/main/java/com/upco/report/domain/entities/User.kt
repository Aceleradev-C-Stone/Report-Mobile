package com.upco.report.domain.entities

import com.upco.report.domain.enums.UserRole
import java.util.*

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val role: UserRole,
    val createdAt: Date
)