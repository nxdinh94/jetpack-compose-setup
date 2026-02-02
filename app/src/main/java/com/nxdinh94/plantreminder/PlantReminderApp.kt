package com.nxdinh94.plantreminder

import android.app.Application
import com.nxdinh94.plantreminder.core.common.AppContainer

class PlantReminderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppContainer.initialize(this)
    }
}
