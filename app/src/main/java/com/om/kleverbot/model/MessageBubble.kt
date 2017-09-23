package com.om.kleverbot.model

import java.util.*

data class MessageBubble(val body: String, val sender: String, val recipient: String,
    val timestamp: String = Date().time.toString())