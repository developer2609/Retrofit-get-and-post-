package com.example.retrofit_get.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_get.models.MyToDo
import com.example.retrofit_get.models.RvAdapter
import com.example.retrofit_get.retrofit.ApiClient
import com.example.retrofit_get.retrofit.ApiServise
import com.example.retrofit_get.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class ToDoViewmodels:ViewModel() {
    private val liveData=MutableLiveData<Resource<List<MyToDo>>>()
    private lateinit var rvAdapter: RvAdapter

    fun getAllTodo():MutableLiveData<Resource<List<MyToDo>>>{
          val apiServise=ApiClient.getApiServise()
        viewModelScope.launch {

            liveData.postValue(Resource.loading("loading"))
            try {
                coroutineScope {
                    val list = async {
                      apiServise.getAllTodo()
                    }.await()
                    liveData.postValue(Resource.successs(list))
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message))
            }
        }

        return liveData
    }




}