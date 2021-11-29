package com.example.authactivity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.authactivity.ui.currency.CurrencyActivity
import java.io.Serializable

@Entity(tableName = "lang")
data class LangData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var amount: Int,
    var income: Int
): Serializable