package com.example.authactivity.local

import android.content.Context
import android.content.SharedPreferences
import javax.xml.namespace.NamespaceContext

class PrefsHelper(private val context: Context) {

    private val PREFS_NAME = "KowumchaApp"
    private val CURRENCY = "CURRENCY"
    private val SALARY = "SALARY"
    private val INCOME = "INCOME"
    private val GUEST = "GUEST"
    private val AMOUNT = "AMOUNT"
    private val NAME = "NAME"
    private val NAME_ID = "NAME_ID"
    private val CATEGORY = "CATEGORY"
    private val LANG = "LANG"
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

    fun getName(): String? {
        return prefs.getString(NAME, "")
    }

    fun saveName(names: String) {
        prefs.edit().putString(NAME, names).apply()
    }

    fun saveNameId(id: Int){
        prefs.edit().putInt(NAME_ID, id).apply()
    }

    fun getNameId(): Int? {
        return prefs.getInt(NAME_ID, 0)
    }

    fun getCategory(): String? {
        return prefs.getString(CATEGORY, "")
    }

    fun saveCategory(category: String) {
        prefs.edit().putString(CATEGORY, category).apply()
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

    fun saveLang(lang: String) {
        prefs.edit().putString(LANG, lang).apply()
    }

    fun getLang(): String? {
        return prefs.getString(LANG, "")
    }

    fun getAmount(): String? {
        return prefs.getString(AMOUNT, "$")
    }

    fun saveAmount(amount: Int) {
        prefs.edit().putString(AMOUNT, amount.toString()).apply()
    }

    companion object {
        lateinit var instance: PrefsHelper
    }
}