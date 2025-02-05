package com.example.roomfinder.session_manager

import android.app.Application

class RoomFinderApplication : Application() {
    lateinit var sessionManager: SessionManager

    override fun onCreate() {
        super.onCreate()
        sessionManager = SessionManager(this)
    }

    companion object {
        lateinit var instance: RoomFinderApplication
            private set
    }
}