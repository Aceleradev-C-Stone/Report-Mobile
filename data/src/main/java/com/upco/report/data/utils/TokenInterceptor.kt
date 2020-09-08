package com.upco.report.data.utils

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val tokenManager: TokenManager
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            if (tokenManager.retrieveToken().isNotEmpty()) {
                val token = tokenManager.retrievePreparedToken()
                request = request.newBuilder()
                    .addHeader("Authorization", token)
                    .build()
            }
        }

        return chain.proceed(request)
    }
}