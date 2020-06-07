package com.burakusluer.kotlinmarvel.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelAPI {
    val api = Retrofit.Builder()
        .baseUrl("https://www.simplifiedcoding.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(APIMarvel::class.java).getData()
}