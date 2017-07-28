package com.om.kleverbot.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ScrollView
import com.om.kleverbot.BuildConfig
import com.om.kleverbot.R
import com.om.kleverbot.api.ApiManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.message_layout.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

  lateinit var replyView: View

  private lateinit var compositeDisposable: CompositeDisposable

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    compositeDisposable = CompositeDisposable()

    sendMessageBTN.setOnClickListener {
      addMessageToView(messageET.text.toString())

      compositeDisposable.add(
          ApiManager.talkToBot(BuildConfig.CLEVERBOT_API_KEY, messageET.text.toString())
              .subscribe({
                result ->
                addMessageToView(result.output)
              }, { error ->
                error.printStackTrace()
              }))

      messageET.setText("")
    }
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
