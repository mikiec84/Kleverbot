package com.om.kleverbot.model

import android.app.Application
import timber.log.Timber

class KleverbotApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    Timber.plant(Timber.DebugTree())
  }
}