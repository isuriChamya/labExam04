package com.example.todoeducation.database

class TaskRepository(
    private val db:TaskDatabase
) {

    suspend fun insert(task: Task) = db.getTaskDao().insert(task)
    suspend fun delete(task: Task) = db.getTaskDao().delete(task)

    fun getAllTaskItems():List<Task> = db.getTaskDao().getAllTaskItems()
}