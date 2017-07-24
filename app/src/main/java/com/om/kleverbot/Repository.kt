package com.om.kleverbot

class Repository(val apiService: ApiService) {

  fun talkToBot(key: String, input: String): io.reactivex.Observable<BotResponse> {
    return apiService.talkToBot(key, input)
  }
}