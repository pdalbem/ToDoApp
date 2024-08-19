package com.ifsp.todoapp.data.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    fun insertTodo(todoItem: TodoItem)

    @Delete
    fun deleteTodo(todoItem: TodoItem)

    @Update
    fun updateTodo(todoModel: TodoItem)

    @Query("SELECT * FROM todo_table ORDER BY id")
    fun getAllTodos(): Flow<MutableList<TodoItem>>
}