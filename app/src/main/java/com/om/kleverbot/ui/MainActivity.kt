package com.om.kleverbot.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ScrollView
import com.om.kleverbot.R
import com.om.kleverbot.repository.ProvideRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.message_layout.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

  lateinit var replyView: View
  val compositeDisposable: CompositeDisposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    val repository = ProvideRepository.provideRepository()

    fab.setOnClickListener {
      addMessageToView(messageET.text.toString())

      compositeDisposable.add(
          repository.talkToBot("CC3enJ_PpO4o01bcYzIWb3Q8zYA", messageET.text.toString())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeOn(Schedulers.io())
              .subscribe({
                result ->
                addMessageToView(result.output)
              }, { error ->
                error.printStackTrace()
              })
      )

      messageET.setText("")
    }
  }

  override fun onDestroy() {
    compositeDisposable.clear()
    super.onDestroy()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId

    if (id == R.id.action_settings) {
      return true
    }

    return super.onOptionsItemSelected(item)
  }

  fun addMessageToView(message: String) {
    replyView = layoutInflater.inflate(R.layout.message_layout, null)
    replyView.messageContentTV.text = message
    messagesLayout.addView(replyView)
    messagesScrollContainer.fullScroll(ScrollView.FOCUS_DOWN)
  }
}
