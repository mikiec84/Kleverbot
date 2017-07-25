package com.om.kleverbot.repository

import com.om.kleverbot.api.ApiManager

object ProvideRepository {

  fun provideRepository(): Repository {
    return Repository(ApiManager.getApiService())
  }
}