package com.burakusluer.kotlinmarvel.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.burakusluer.kotlinmarvel.model.ModelMarvel

@Database(entities = [ModelMarvel::class], version = 2)
abstract class DataBaseMarvel : RoomDatabase() {
    abstract fun dao(): DAOMarvel

    companion object {
        private val lock = Any()
        private var instance: DataBaseMarvel? = null
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            Room.databaseBuilder(context, DataBaseMarvel::class.java, "Marvel")
                .fallbackToDestructiveMigration().build()
        }.also {
            instance = it
        }
    }
}