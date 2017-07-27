package com.om.kleverbot.ui.base

interface BasePresenter<in V: BaseView>{

  fun attachView(view: V)

  fun detachView()

  fun initialiseCompositeDisposable()

  fun clearCompositeDisposable()
}