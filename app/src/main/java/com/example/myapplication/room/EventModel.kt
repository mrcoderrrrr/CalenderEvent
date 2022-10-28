package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EventTable")
data class EventModel(
    @PrimaryKey(autoGenerate = true)
    val  id:Int,
    var tilte:String,
    var desc:String,
    var date:String
)
