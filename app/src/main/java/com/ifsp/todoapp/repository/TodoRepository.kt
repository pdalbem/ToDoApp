package com.ifsp.todoapp.repository

import androidx.annotation.WorkerThread
import com.ifsp.todoapp.data.datasource.database.TodoDao
import com.ifsp.todoapp.data.datasource.database.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@ActivityRetainedScoped
class TodoRepository @Inject constructor(private val todoDao: TodoDao){

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
    suspend fun insert(todoItem: TodoItem){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.insertTodo(todoItem)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(todoItem: TodoItem){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.deleteTodo(todoItem)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(todoItem: TodoItem){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.updateTodo(todoItem)
        }
    }

    val getAllTodos: Flow<MutableList<TodoItem>> = todoDao.getAllTodos()
//    = todoDao.getAllTodos().flowOn(Dispatchers.IO).conflate()
}