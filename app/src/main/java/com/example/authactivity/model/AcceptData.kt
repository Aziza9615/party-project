package com.example.authactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "accept")
data class AcceptData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var category: String,
    var amount: Int
) : Serializable