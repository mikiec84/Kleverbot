package com.om.kleverbot.main

import android.content.Context
import com.om.kleverbot.api.ApiManager
import com.om.kleverbot.database.MySqlHelper
import com.om.kleverbot.database.database
import com.om.kleverbot.model.MessageBubble
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync

class MainActivityPresenter constructor(private val context: Context) {

  fun sendMessageToBot(input: String) {
    ApiManager.sendMessageToBot(input)
        .subscribe({ result ->

          val receivedMessage = MessageBubble(body = result.output, sender = "Kleverbot",
              recipient = "You")

          (context as MainActivityView).addMessageToView(receivedMessage)

          addMessageToDB(receivedMessage)

        },
            { error -> error.printStackTrace() })

  }

  fun addMessageToDB(newMessage: MessageBubble) {
    doAsync {
      bg {
        context.database.use {
          insert(MySqlHelper.tableName,
              "body" to newMessage.body,
              "sender" to newMessage.sender,
              "recipient" to newMessage.recipient,
              "timestamp" to newMessage.timestamp
          )
        }
      }
    }
  }

  fun getAllMessages() = doAsync {
    bg {
      val allMessages = ArrayList<MessageBubble>()

      context.database.use {
        select(MySqlHelper.tableName).parseList(object : MapRowParser<List<MessageBubble>> {

          override fun parseRow(columns: Map<String, Any?>): ArrayList<MessageBubble> {

            val body = columns.getValue("body")
            val sender = columns.getValue("sender")
            val recipient = columns.getValue("recipient")
            val timestamp = columns.getValue("timestamp")

            val message = MessageBubble(body as String, sender as String, recipient as String,
                timestamp as String)

            allMessages.add(message)

            return allMessages
          }
        })
      }

      (context as MainActivityView).displayMessagesCount(allMessages)
    }
  }
}