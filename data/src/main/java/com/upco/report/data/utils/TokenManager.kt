package com.upco.report.data.utils

import android.content.Context
import com.upco.report.data.R

class TokenManager(context:Context) {

    private val prefs = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun retrieveToken(): String {
        return prefs.getString(USER_TOKEN, "")!!
    }

    fun retrievePreparedToken(): String {
        return "Bearer ${ retrieveToken() }"
    }

    companion object {
        const val USER_TOKEN = "user_token"
    }
}