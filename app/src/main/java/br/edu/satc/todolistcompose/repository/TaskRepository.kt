package br.edu.satc.todolistcompose.repository

import br.edu.satc.todolistcompose.data.TaskDao
import br.edu.satc.todolistcompose.data.TaskEntity

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insert(task: TaskEntity) = taskDao.insert(task)
    suspend fun getAll(): List<TaskEntity> = taskDao.getAll()
    suspend fun update(task: TaskEntity) = taskDao.update(task)
    suspend fun delete(task: TaskEntity) = taskDao.delete(task)
}