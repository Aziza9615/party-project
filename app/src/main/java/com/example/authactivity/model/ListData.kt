package com.example.authactivity.model

import androidx.room.PrimaryKey
import java.io.Serializable

data class ListData(
        @PrimaryKey(autoGenerate = true)
        var userName: String,
        var userSub: String,
        var userAm: String
):Serializable