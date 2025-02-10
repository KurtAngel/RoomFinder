package com.example.coroutine.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.roomfinder.model.Student
import com.google.gson.Gson

class StudentRepository(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "student_prefs",
        Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveStudent(student: Student) {
        val studentJson = gson.toJson(student)
        sharedPreferences.edit()
            .putString(STUDENT_KEY, studentJson)
            .apply()
    }

    fun getStudent(): Student? {
        val studentJson = sharedPreferences.getString(STUDENT_KEY, null)
        return if (studentJson != null) {
            gson.fromJson(studentJson, Student::class.java)
        } else {
            null
        }
    }

    fun clearStudent() {
        sharedPreferences.edit().remove(STUDENT_KEY).apply()
    }

    companion object {
        private const val STUDENT_KEY = "student_data"
    }
} 