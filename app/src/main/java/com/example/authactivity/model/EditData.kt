package com.example.authactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "edit")
data class EditData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var category: String? = null,
    var amount: Int? = null
) : Serializable