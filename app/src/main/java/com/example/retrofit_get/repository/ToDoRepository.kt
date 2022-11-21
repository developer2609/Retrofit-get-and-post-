package com.example.retrofit_get.repository

import com.example.retrofit_get.retrofit.ApiServise

class ToDoRepository(val apiServise: ApiServise) {

    suspend fun  getAllToDo()=apiServise.getAllTodo()
}