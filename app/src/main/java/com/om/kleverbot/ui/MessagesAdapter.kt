package com.om.kleverbot.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.om.kleverbot.R
import com.om.kleverbot.classes.MessageBubble
import kotlinx.android.synthetic.main.kleverbot_message_layout.view.*

class MessagesAdapter(private val messageBubbles: List<MessageBubble>,
    private val onClick: (MessageBubble) -> Unit)
  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private val YOUR_MESSAGE = 1
  private val KLEVERBOT_MESSAGE = 2

  override fun getItemCount(): Int = messageBubbles.size

  override fun getItemViewType(position: Int): Int =
      when (messageBubbles[position].sender) {
        "You" -> {
          YOUR_MESSAGE
        }
        "Kleverbot" -> {
          KLEVERBOT_MESSAGE
        }
        else -> -1
      }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
    return if (viewType == YOUR_MESSAGE) {
      LayoutInflater.from(parent?.context)
          .inflate(R.layout.your_message_layout, parent, false).let {
        YourMessagesViewHolder(it, onClick)
      }
    } else {
      LayoutInflater.from(parent?.context)
          .inflate(R.layout.kleverbot_message_layout, parent, false).let {
        KleverbotMessagesViewHolder(it, onClick)
      }
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (holder.itemViewType) {
      YOUR_MESSAGE -> {
        val yourHolder = holder as YourMessagesViewHolder
        yourHolder.bindData(messageBubbles[position])
      }
      KLEVERBOT_MESSAGE -> {
        val kleverbotHolder = holder as KleverbotMessagesViewHolder
        kleverbotHolder.bindData(messageBubbles[position])
      }
    }
  }

  class YourMessagesViewHolder(itemView: View,
      private val onClick: (MessageBubble) -> Unit) : RecyclerView.ViewHolder(
      itemView) {

    fun bindData(messageBubble: MessageBubble) {
      with(messageBubble) {
        itemView.messageContentTV.text = body
        itemView.setOnClickListener { onClick(this) }
      }
    }
  }

  class KleverbotMessagesViewHolder(itemView: View,
      private val onClick: (MessageBubble) -> Unit) : RecyclerView.ViewHolder(
      itemView) {

    fun bindData(messageBubble: MessageBubble) {
      with(messageBubble) {
        itemView.messageContentTV.text = body
        itemView.setOnClickListener { onClick(this) }
      }
    }
  }
}
