package com.example.retrofit_get.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

      const val BASE_URL="https://hvax.pythonanywhere.com/"

    fun getRetrofit(): Retrofit{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiServise():ApiServise{
        return getRetrofit().create(ApiServise::class.java)
    }
}