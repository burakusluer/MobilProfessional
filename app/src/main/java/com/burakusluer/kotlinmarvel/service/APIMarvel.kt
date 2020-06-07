package com.burakusluer.kotlinmarvel.service

import com.burakusluer.kotlinmarvel.model.ModelMarvel
import io.reactivex.Single
import retrofit2.http.GET

interface APIMarvel {

    @GET("demos/marvel/")
    fun getData(): Single<List<ModelMarvel>>
}