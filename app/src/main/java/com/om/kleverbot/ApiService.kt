package com.om.kleverbot

interface ApiService {

  @retrofit2.http.GET("getreply")
  fun talkToBot(@retrofit2.http.Query("key") key: String,
      @retrofit2.http.Query("input") input: String): io.reactivex.Observable<BotResponse>

  /**
   * Companion object for the factory
   */
  companion object Factory {
    fun create(): ApiService {

//      val loggingInterceptor = HttpLoggingInterceptor()
//      loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//      val httpClient = OkHttpClient.Builder()
//      httpClient.readTimeout(45, TimeUnit.SECONDS)
//      httpClient.writeTimeout(45, TimeUnit.SECONDS)
//      httpClient.addInterceptor(loggingInterceptor)

      val retrofit = retrofit2.Retrofit.Builder()
          .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
          .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
          .baseUrl("https://www.cleverbot.com/")
          .build()

      return retrofit.create(ApiService::class.java)
    }
  }
}