package com.om.kleverbot.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ApiManager {
  private const val SERVER: String = "https://www.cleverbot.com/"

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

  fun getApiService(): ApiService = apiService
}