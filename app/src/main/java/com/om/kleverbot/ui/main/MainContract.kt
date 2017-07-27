package com.om.kleverbot.ui.main

import com.om.kleverbot.ui.base.BasePresenter
import com.om.kleverbot.ui.base.BaseView

object MainContract {

  interface View : BaseView {
    fun addMessageToView(message: String)
  }

  interface Presenter : BasePresenter<View> {
    fun talkToBot(input: String)
  }
}