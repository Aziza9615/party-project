package com.example.authactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contact")
data class ContactData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String?,
    var category: String? = null,
    var amount: Int? = null
) : Serializable