package com.example.retrofit_get.utils

import java.util.*

data class Resource<out T>(var status: Status,val date:T?,val message:String?){
    companion object{
        fun <T>successs(data:T):Resource<T>{
            return Resource(Status.Succesful,data,null)
        }
        fun <T>error(message: String?):Resource<T>{
            return Resource(Status.Error,null,message)
        }
        fun <T>loading(message: String?):Resource<T>{
            return Resource(Status.Loading,null, message)
        }
    }
}
