package com.example.innowiseapplicationlifecycle

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefs(
    private val prefs: SharedPreferences
) {
    fun getCounterToken(): Int = prefs.getInt(COUNTER_KEY, DEFAULT)

    fun setCounterToken(token: Int) = prefs.edit {
        putInt(COUNTER_KEY, token)
    }

    fun getTimeToken(): Long = prefs.getLong(TIME_KEY, DEFAULT.toLong())

    fun setTimeToken(token: Long) = prefs.edit {
        putLong(TIME_KEY, token)
    }

    companion object {
        private const val DEFAULT = 0
        private const val COUNTER_KEY = "counter"
        private const val TIME_KEY = "time"
    }
}