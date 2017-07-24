package com.om.kleverbot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.message_layout.view.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    fab.setOnClickListener {
      val messageLayout = layoutInflater.inflate(R.layout.message_layout, null)

//      val messageContentTV = messageLayout.findViewById(R.id.messageContentTV) as TextView

      // Extensions work on concrete instance of view. So part `messageLayout.` in `messageLayout.messageContentTV` is crucial here
      messageLayout.messageContentTV.text = messageET.text

      messageET.setText("")

      messagesLayout.addView(messageLayout)
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
}
