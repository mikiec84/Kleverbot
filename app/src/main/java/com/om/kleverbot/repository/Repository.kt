package com.om.kleverbot.repository

import com.om.kleverbot.api.BotResponse
import com.om.kleverbot.api.ApiService

class Repository(val apiService: ApiService) {

  fun talkToBot(key: String, input: String): io.reactivex.Observable<BotResponse> {
    return apiService.talkToBot(key, input)
  }
}