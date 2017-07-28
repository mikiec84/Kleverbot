package com.om.kleverbot.ui.base

import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {

  protected var view: V? = null
  protected lateinit var compositeDisposable: CompositeDisposable

  override fun attachView(view: V) {
    this.view = view
    Timber.d("attachView")

  }

  override fun detachView() {
    view = null
  }

  override fun initialiseCompositeDisposable() {
    compositeDisposable = CompositeDisposable()
    Timber.d("")
  }

  override fun clearCompositeDisposable() {
    compositeDisposable.clear()
  }
}