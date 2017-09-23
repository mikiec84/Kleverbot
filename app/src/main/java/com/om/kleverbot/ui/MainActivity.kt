package com.om.kleverbot.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.om.kleverbot.R
import com.om.kleverbot.api.ApiManager
import com.om.kleverbot.classes.MessageBubble
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class MainActivity : AppCompatActivity() {

  private val compositeDisposable: CompositeDisposable by lazy {
    CompositeDisposable()
  }

  private val messageBubbles: ArrayList<MessageBubble> by lazy {
    ArrayList<MessageBubble>()
  }

  var messagesAdapter: MessagesAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    setupMessagesRecycler()

    sendMessageBTN.setOnClickListener {
      addNewBubbleMessage(messageET.text.toString(), "You", "Kleverbot")
      sendMessageToBot(messageET.text.toString())
      messageET.setText("")
    }
  }

  private fun setupMessagesRecycler() {
    messagesAdapter = MessagesAdapter(messageBubbles,
        {
          Toast.makeText(this, "Message timestamp is : ${it.timestamp}", Toast.LENGTH_LONG).show()
        })
    messagesRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    messagesRecycler.adapter = messagesAdapter
  }

  private fun addNewBubbleMessage(body: String, sender: String, recipient: String) {
    messageBubbles.add(
        MessageBubble(body, Date().time.toString(), sender, recipient))

    messagesAdapter?.notifyDataSetChanged()
    messagesRecycler.scrollToPosition(messageBubbles.size - 1)
  }

  private fun sendMessageToBot(input: String) {
    compositeDisposable.add(
        ApiManager.talkToBot(input)
            .subscribe({ result ->
              addNewBubbleMessage(result.output, "Kleverbot", "You")
            },
                { error -> error.printStackTrace() }))
  }
}
