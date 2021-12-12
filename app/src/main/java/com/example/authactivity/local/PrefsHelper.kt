package com.example.authactivity.local

import android.content.Context
import android.content.SharedPreferences

class PrefsHelper(private val context: Context) {

    private val PREFS_NAME = "KowumchaApp"
    private val CURRENCY = "CURRENCY"
    private val SALARY = "SALARY"
    private val INCOME = "INCOME"
    private val GUEST = "GUEST"
    private var prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    init {
        instance = this
    }

    fun getCurrency(): String? {
        return prefs.getString(CURRENCY, "$")
    }

    fun saveCurrency(currency: String) {
        prefs.edit().putString(CURRENCY, currency).apply()
    }

    fun saveSalary(salary: Int) {
        prefs.edit().putInt(SALARY, salary).apply()
    }

    fun getSalary(): Int? {
        return prefs.getInt(SALARY, 0)
    }

    fun getIncome(): Int? {
        return prefs.getInt(INCOME, 0)
    }

    fun saveIncome(income: Int) {
        prefs.edit().putInt(INCOME, income).apply()
    }

   fun getGuest(): Boolean {
     return prefs.getBoolean(GUEST, false)
   }

   fun saveGuest(guest: Boolean) {
       prefs.edit().putBoolean(GUEST, guest).apply()
   }

    companion object {
        lateinit var instance: PrefsHelper
    }
}