package com.example.roomfinder.session_manager

import android.content.Context

class SessionManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("RoomFinderPrefs", Context.MODE_PRIVATE)

    var authToken: String?
        get() = sharedPreferences.getString("auth_token", null)
        set(value) = sharedPreferences.edit().putString("auth_token", value).apply()

    var userId: Int?
        get() = sharedPreferences.getInt("user_id", -1).takeIf { it != -1 }
        set(value) = sharedPreferences.edit().putInt("user_id", value ?: -1).apply()

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}