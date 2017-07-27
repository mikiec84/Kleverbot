package com.om.kleverbot.ui.main

import com.om.kleverbot.BuildConfig
import com.om.kleverbot.api.ApiManager
import com.om.kleverbot.ui.base.BasePresenterImpl

class MainPresenter : BasePresenterImpl<MainContract.View>(), MainContract.Presenter {

  override fun talkToBot(input: String) {
    compositeDisposable.add(ApiManager.talkToBot(BuildConfig.CLEVERBOT_API_KEY, input)
        .subscribe({
          result ->
          view?.addMessageToView(result.output)
        }, { error ->
          error.printStackTrace()
        }))
  }
}