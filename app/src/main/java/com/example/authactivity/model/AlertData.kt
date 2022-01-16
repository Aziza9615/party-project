package com.example.authactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "alert")
data class AlertData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String
) : Serializable