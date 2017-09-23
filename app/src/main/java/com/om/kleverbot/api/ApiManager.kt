package com.om.kleverbot.api

import com.om.kleverbot.BuildConfig
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ApiManager {
  private const val SERVER: String = "https://www.cleverbot.com/"

  const val GET_REPLY_ENDPOINT = "getreply"

  private lateinit var apiService: ApiService

  init {
    val retrofit = initRetrofit()
    initServices(retrofit)
  }

  private fun initRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
    client.readTimeout(45, TimeUnit.SECONDS)
    client.writeTimeout(45, TimeUnit.SECONDS)
    client.addInterceptor(loggingInterceptor)

    return Retrofit.Builder()
        .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .client(client.build())
        .baseUrl(SERVER)
        .build()
  }

  private fun initServices(retrofit: Retrofit) {
    apiService = retrofit.create(ApiService::class.java)
  }

  /**
   * To avoid having to get API Service from this class as an instance and then do the call,
   * call it directly from here to be able to grab its output as a single unit and subscribe/observe wherever you like
   */
  fun sendMessageToBot(input: String): Observable<BotResponse> =
      apiService.sendMessageToBot(BuildConfig.CLEVERBOT_API_KEY, input).subscribeOn(
          Schedulers.io()).observeOn(
          AndroidSchedulers.mainThread())
}