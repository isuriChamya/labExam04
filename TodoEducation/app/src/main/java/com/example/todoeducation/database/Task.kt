package com.example.todoeducation.database

import android.content.ClipData.Item
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    var item: String?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
