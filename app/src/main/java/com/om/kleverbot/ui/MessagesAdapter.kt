package com.om.kleverbot.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.om.kleverbot.R
import com.om.kleverbot.classes.MessageBubble
import kotlinx.android.synthetic.main.message_layout.view.*


class MessagesAdapter(val context: Context, private val messageBubbles: List<MessageBubble>,
    val onClick: (MessageBubble) -> Unit)
  : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    holder?.bindData(messageBubbles[position])
  }

  override fun getItemCount(): Int = messageBubbles.size

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    return LayoutInflater.from(parent?.context)
        .inflate(R.layout.message_layout, parent, false).let {
      ViewHolder(context, it, onClick)
    }
  }

  class ViewHolder(val context: Context, itemView: View,
      val onClick: (MessageBubble) -> Unit) : RecyclerView.ViewHolder(
      itemView) {

    fun bindData(messageBubble: MessageBubble) {
      val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
          RelativeLayout.LayoutParams.WRAP_CONTENT)

      params.setMargins(0, 0, 0,
          context.resources.getDimensionPixelSize(R.dimen.bottom_padding_between_messages))
      itemView.layoutParams = params

      with(messageBubble) {
        if (sender == "You")
          itemView.setBackgroundResource(R.drawable.your_messages_bg)
        else
          itemView.setBackgroundResource(R.drawable.their_messages_bg)

        itemView.messageContentTV.text = body
        itemView.setOnClickListener { onClick(this) }
      }
    }
  }
}
