package com.example.roomfinder

import android.app.Application
import com.example.roomfinder.session_manager.SessionManager

class RoomFinderApplication : Application() {

    lateinit var sessionManager: SessionManager

    override fun onCreate() {
        super.onCreate()
        instance = this
        sessionManager = SessionManager(this)
    }

    companion object {
        lateinit var instance: RoomFinderApplication
            private set
    }
}