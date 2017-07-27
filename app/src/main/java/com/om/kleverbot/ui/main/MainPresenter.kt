package com.om.kleverbot.ui.main

import com.om.kleverbot.api.ApiManager
import com.om.kleverbot.ui.base.BasePresenterImpl

class MainPresenter : BasePresenterImpl<MainContract.View>(), MainContract.Presenter {

  override fun talkToBot(input: String) {
    compositeDisposable.add(ApiManager.talkToBot("CC3enJ_PpO4o01bcYzIWb3Q8zYA", input)
        .subscribe({
          result ->
          view?.addMessageToView(result.output)
        }, { error ->
          error.printStackTrace()
        }))
  }
}