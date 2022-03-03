package com.example.numanvaccine.data.di

import android.content.Context
import androidx.room.Room
import com.example.numanvaccine.data.room.db.CountryDao
import com.example.numanvaccine.data.room.db.CountryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideCollectionDatabase(@ApplicationContext context: Context): CountryDatabase {
        return Room
            .databaseBuilder(
                context,
                CountryDatabase::class.java,
                CountryDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCollectionDao(database: CountryDatabase): CountryDao {
        return database.countryDao()
    }

}
