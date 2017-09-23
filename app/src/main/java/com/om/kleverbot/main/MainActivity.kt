package com.om.kleverbot.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.om.kleverbot.R
import com.om.kleverbot.model.MessageBubble
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class MainActivity : AppCompatActivity(), MainActivityView {
  private val messageBubbles: ArrayList<MessageBubble> by lazy {
    ArrayList<MessageBubble>()
  }

  private val presenter = MainActivityPresenter(this)

  private var messagesAdapter: MessagesAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    setupMessagesRecycler()

    presenter.getAllMessages()

    sendMessageBTN.setOnClickListener {
      val sentMessage = MessageBubble(body = messageET.text.toString(), sender = "You",
          recipient = "Kleverbot")

      addMessageToView(sentMessage)

      presenter.sendMessageToBot(sentMessage.body)

      presenter.addMessageToDB(sentMessage)

      messageET.setText("")
    }
  }

  override fun displayMessagesCount(messages: List<MessageBubble>) {
    messages.forEach { addMessageToView(it) }
  }

  override fun addMessageToView(message: MessageBubble) {
    val newMessage = MessageBubble(message.body, message.sender, message.recipient,
        Date().time.toString())

    messageBubbles.add(newMessage)

    messagesAdapter?.notifyDataSetChanged()
    messagesRecycler.scrollToPosition(messageBubbles.size - 1)
  }

  private fun setupMessagesRecycler() {
    messagesAdapter = MessagesAdapter(messageBubbles,
        {
          Toast.makeText(this, "Message timestamp is : ${it.timestamp}", Toast.LENGTH_LONG).show()
        })
    messagesRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    messagesRecycler.adapter = messagesAdapter
  }
}
