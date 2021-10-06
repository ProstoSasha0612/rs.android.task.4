package com.hfad.android.storageapp

import android.app.Application
import com.hfad.android.storageapp.database.Repository

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
    }
}