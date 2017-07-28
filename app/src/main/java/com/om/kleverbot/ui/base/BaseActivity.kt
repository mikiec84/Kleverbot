package com.om.kleverbot.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import timber.log.Timber

abstract class BaseActivity<in V : BaseView, T : BasePresenter<V>> : AppCompatActivity(), BaseView {

  protected abstract var presenter: T

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter.attachView(this as V)
    presenter.initialiseCompositeDisposable()
    Timber.d("onCreate")
  }

  override fun getContext(): Context = this

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
    presenter.clearCompositeDisposable()
  }

  override fun checkInternetAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
  }

  override fun displayNoInternetDialog() {
    val alertDialog = AlertDialog.Builder(this).create()
    alertDialog.setTitle("Connection Error")
    alertDialog.setMessage(
        "Internet not available, check your internet connectivity and try again")
    alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", { dialogInterface, i -> finish() })
  }

  override fun displayRequestErrorDialog(responseMessage: String) {
    val alertDialog = AlertDialog.Builder(this).create()
    alertDialog.setTitle("Connection Error")
    alertDialog.setMessage(
        "The server returned the following error $responseMessage")
    alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
        { dialogInterface, i -> dialogInterface.dismiss() })
    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Report", { dialogInterface, i -> finish() })
  }
}