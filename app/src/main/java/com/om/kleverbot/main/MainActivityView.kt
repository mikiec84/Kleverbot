package com.om.kleverbot.main

import com.om.kleverbot.model.MessageBubble

interface MainActivityView {
  fun addMessageToView(message: MessageBubble)

  fun displayMessagesCount(messages: List<MessageBubble>)
}