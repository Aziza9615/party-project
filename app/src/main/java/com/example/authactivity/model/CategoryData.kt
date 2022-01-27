package com.example.authactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "category")
data class CategoryData (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var category: String? = null
) : Serializable