package com.om.kleverbot.ui.base

import android.content.Context

interface BaseView {
  fun checkInternetAvailable(): Boolean

  fun displayNoInternetDialog()

  fun displayRequestErrorDialog(responseMessage: String)

  fun getContext(): Context
}