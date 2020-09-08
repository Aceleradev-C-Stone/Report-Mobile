package com.upco.report.domain.repository

import com.upco.report.domain.entities.User
import com.upco.report.domain.responses.ResultRequired
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(email: String, password: String): Flow<ResultRequired<User>>
    fun register(name: String, email: String, password: String): Flow<ResultRequired<User>>
}