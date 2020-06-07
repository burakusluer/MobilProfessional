package com.burakusluer.kotlinmarvel.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.burakusluer.kotlinmarvel.model.ModelMarvel

@Dao
interface DAOMarvel {

    @Insert
    suspend fun insertALL(vararg hero: ModelMarvel): List<Long>

    @Query("delete from heroes")
    suspend fun deleteAll(): Int

    @Query("select * from heroes")
    suspend fun getAll(): List<ModelMarvel>

    @Query("select * from heroes where pk=:uuid")
    suspend fun getSingleUUID(uuid: Int): ModelMarvel
}