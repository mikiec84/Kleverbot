package com.om.kleverbot

object ProvideRepository {

  fun provideRepository(): Repository {
    return Repository(ApiService.Factory.create())
  }
}