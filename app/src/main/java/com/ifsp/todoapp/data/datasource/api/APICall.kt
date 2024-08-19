package com.ifsp.todoapp.data.datasource.api

import com.ifsp.todoapp.data.datasource.database.TodoItem
import retrofit2.Response
import retrofit2.http.GET

interface APICall {
    @GET("todos")
    suspend fun getTodos(): Response<List<TodoItem>>
}