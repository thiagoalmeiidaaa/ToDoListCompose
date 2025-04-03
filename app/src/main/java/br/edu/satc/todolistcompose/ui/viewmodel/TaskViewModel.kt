package br.edu.satc.todolistcompose.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.repository.TaskRepository
import br.edu.satc.todolistcompose.data.TaskEntity
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = AppDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(taskDao)

    var tasks = mutableStateOf(listOf<TaskEntity>())
        private set

    init {
        viewModelScope.launch {
            tasks.value = repository.getAll()
        }
    }

    fun addTask(title: String, description: String) {
        val newTask = TaskEntity(title = title, description = description)
        viewModelScope.launch {
            repository.insert(newTask)
            tasks.value = repository.getAll()
        }
    }

    fun toggleTask(task: TaskEntity) {
        val updatedTask = task.copy(isDone = !task.isDone)
        viewModelScope.launch {
            repository.update(updatedTask)
            tasks.value = repository.getAll()
        }
    }
}
