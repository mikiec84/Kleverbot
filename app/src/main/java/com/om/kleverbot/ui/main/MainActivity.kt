package com.om.kleverbot.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ScrollView
import com.om.kleverbot.R
import com.om.kleverbot.ui.base.BaseActivity
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.message_layout.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

  lateinit var replyView: View

  override var presenter: MainContract.Presenter = MainPresenter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    fab.setOnClickListener {
      addMessageToView(messageET.text.toString())

      presenter.talkToBot(messageET.text.toString())

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

  override fun addMessageToView(message: String) {
    replyView = layoutInflater.inflate(R.layout.message_layout, null)
    replyView.messageContentTV.text = message
    messagesLayout.addView(replyView)
    messagesScrollContainer.fullScroll(ScrollView.FOCUS_DOWN)
  }
}
