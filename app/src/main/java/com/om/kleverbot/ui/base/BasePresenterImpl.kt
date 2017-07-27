package com.om.kleverbot.ui.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {

  protected var view: V? = null
  protected lateinit var compositeDisposable: CompositeDisposable

  override fun attachView(view: V) {
    this.view = view
  }

  override fun detachView() {
    view = null
  }

  override fun initialiseCompositeDisposable() {
    compositeDisposable = CompositeDisposable()
  }

  override fun clearCompositeDisposable() {
    compositeDisposable.clear()
  }
}