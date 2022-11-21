package com.example.retrofit_get.retrofit

import com.example.retrofit_get.models.MyToDo
import com.example.retrofit_get.models.MyToDpPostRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServise {

    @GET("plan")
    suspend fun getAllTodo():List<MyToDo>

    @POST("plan/")
    fun addToDo(@Body myToDpPostRequest: MyToDpPostRequest):Call<MyToDo>

}