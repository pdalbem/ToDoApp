package com.ifsp.todoapp.data.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoItem(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    var title: String?,
)