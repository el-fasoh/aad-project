package com.fasoh.trialproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProjectApp : Application(){
    override fun onCreate() {
        super.onCreate()

    }
}