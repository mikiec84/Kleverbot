package com.om.kleverbot.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.om.kleverbot.R
import com.om.kleverbot.classes.MessageBubble
import kotlinx.android.synthetic.main.message_layout.view.*
import timber.log.Timber

class MessagesAdapter(private val messageBubbles: List<MessageBubble>,
    val onClick: (MessageBubble) -> Unit)
  : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    holder?.bindData(messageBubbles[position])
  }

  override fun getItemCount(): Int = messageBubbles.size

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    return LayoutInflater.from(parent?.context)
        .inflate(R.layout.message_layout, parent, false).let {
      ViewHolder(it, onClick)
    }
  }

  class ViewHolder(itemView: View, val onClick: (MessageBubble) -> Unit) : RecyclerView.ViewHolder(
      itemView) {

    fun bindData(messageBubble: MessageBubble) {
      with(messageBubble) {
        itemView.messageContentTV.text = body
        Timber.d("The body was $body")
        itemView.setOnClickListener { onClick(this) }
      }
    }
  }
}
