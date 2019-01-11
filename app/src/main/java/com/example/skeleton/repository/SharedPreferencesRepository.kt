package com.example.skeleton.repository

import android.content.SharedPreferences

class SharedPreferencesRepository(private val sharedPreferences: SharedPreferences) {
    fun userId(): String = sharedPreferences.getString(USER_ID, "")!!

    fun setUserId(userId: String) = sharedPreferences.edit()
        .putString(USER_ID, userId)
        .apply()

    private fun remove(key: String) = sharedPreferences.edit().remove(key).apply()

    fun removeUserId() = remove(USER_ID)

    private companion object {
        const val USER_ID = "user_id"
    }
}