package com.gianlucaveschi.stockpricestrackercompose.ui

import android.app.Application
import com.gianlucaveschi.stockpricestrackercompose.BuildConfig
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}