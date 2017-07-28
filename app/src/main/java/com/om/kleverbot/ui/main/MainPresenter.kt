package com.om.kleverbot.ui.main

import com.om.kleverbot.BuildConfig
import com.om.kleverbot.api.ApiManager
import com.om.kleverbot.ui.base.BasePresenterImpl
import timber.log.Timber

class MainPresenter : BasePresenterImpl<MainContract.View>(), MainContract.Presenter {

  override fun talkToBot(input: String) {
    Timber.d("talkToBot")

    compositeDisposable.add(ApiManager.talkToBot(BuildConfig.CLEVERBOT_API_KEY, input)
        .subscribe({
          result ->
          view?.addMessageToView(result.output)
        }, { error ->
          error.printStackTrace()
        }))
  }
}