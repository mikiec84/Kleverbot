package com.om.kleverbot.api

interface ApiService {

  @retrofit2.http.GET(ApiManager.GET_REPLY_ENDPOINT)
  fun sendMessageToBot(@retrofit2.http.Query("key") key: String,
      @retrofit2.http.Query("input") input: String): io.reactivex.Observable<BotResponse>
}