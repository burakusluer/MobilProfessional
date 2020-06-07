package com.burakusluer.kotlinmarvel.util

import android.content.Context
import android.content.SharedPreferences

abstract class CustomSharedPreferences {
    companion object {
        private val lock = Any()
        private var instance: SharedPreferences? = null
        operator fun invoke(context: Context) = instance ?: kotlin.synchronized(lock) {
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        }.also {
            instance = it
        }
    }
}