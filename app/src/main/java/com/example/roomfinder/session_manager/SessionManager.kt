package com.example.roomfinder.session_manager

import android.content.Context
import com.example.roomfinder.model.Student

class SessionManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("RoomFinderPrefs", Context.MODE_PRIVATE)

    var username: String?
        get() = sharedPreferences.getString("username", null)
        set(value) = sharedPreferences.edit().putString("username", value).apply()

    private var studentNumber: String?
        get() = sharedPreferences.getString("student_number", null)
        set(value) = sharedPreferences.edit().putString("student_number", value).apply()

    var email: String?
        get() = sharedPreferences.getString("email", null)
        set(value) = sharedPreferences.edit().putString("email", value).apply()

    var authToken: String?
        get() = sharedPreferences.getString("auth_token", null)
        set(value) = sharedPreferences.edit().putString("auth_token", value).apply()

    var userId: Int?
        get() = sharedPreferences.getInt("user_id", -1).takeIf { it != -1 }
        set(value) = sharedPreferences.edit().putInt("user_id", value ?: -1).apply()

    fun saveUser(student: Student) {
        with(sharedPreferences.edit()) {
            putString("username", student.username)
            putString("student_number", student.studentNumber)
            putString("email", student.email)
            apply()
        }
    }

    fun getUser(): Student? {
        val username = username
        val studentNumber = studentNumber
        val email = email
        return if (email != null) {
            Student(
                username = username,
                studentNumber = studentNumber,
                email = email,
                password = "" // We don't store password
            )
        } else null
    }

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}