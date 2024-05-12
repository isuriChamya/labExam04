package com.example.todoeducation.database

import android.security.identity.AccessControlProfileId
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTaskItems():List<Task>

    @Query("SELECT * FROM Task WHERE id=:id")
    fun getOne(id: Int):Task
}