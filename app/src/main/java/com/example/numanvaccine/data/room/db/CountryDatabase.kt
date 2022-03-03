package com.example.numanvaccine.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.numanvaccine.data.room.model.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao() : CountryDao

    companion object {
        val DATABASE_NAME = "vaccinated_db.sqlite"
    }
}