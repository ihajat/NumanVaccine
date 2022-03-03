package com.example.numanvaccine.data.room.db

import androidx.room.Dao
import androidx.room.Query
import com.example.numanvaccine.data.room.model.CountryEntity

@Dao
abstract class CountryDao : BaseDao<CountryEntity> {
    @Query("delete from countries")
    abstract suspend fun nukeTable()

    @Query("select * from countries")
    abstract suspend fun getAll(): List<CountryEntity>

    @Query("select * from countries where favourite = 1")
    abstract suspend fun getFavourites(): List<CountryEntity>

}