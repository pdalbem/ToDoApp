package com.ifsp.todoapp.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ifsp.todoapp.data.datasource.database.TodoItem
import com.ifsp.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository): ViewModel(){

    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())

    val todoList =  _todoList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getAllTodos
                .collect{
                        listOfTodos ->
                    if (listOfTodos.isEmpty()){
                        Log.d("Empty", ": Empty List")
                    }
                    else{
                        _todoList.value = listOfTodos
                    }
                }
        }
    }

    val allTodoItems : LiveData<MutableList<TodoItem>>
            = todoRepository.getAllTodos.asLiveData()

    fun insert(todoItem: TodoItem) = viewModelScope.launch {
        todoRepository.insert(todoItem)
    }

    fun delete(todoItem: TodoItem) = viewModelScope.launch {
        todoRepository.delete(todoItem)
    }

    fun update(todoItem: TodoItem) = viewModelScope.launch {
        todoRepository.update(todoItem)
    }

//    class TodoViewModelFactory(private val todoRepository: TodoRepository)
//        : ViewModelProvider.Factory{
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(TodoViewModel::class.java)){
//                @Suppress("UNCHECKED_CAST")
//                return TodoViewModel(todoRepository) as T
//            }
//            throw IllegalArgumentException("Unknown VieModel Class")
//        }
//        }
}
