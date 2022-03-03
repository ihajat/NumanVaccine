package com.example.numanvaccine.data.di

import com.example.numanvaccine.data.network.mappers.NetworkMapper
import com.example.numanvaccine.data.network.services.VaccinatedCountriesService
import com.example.numanvaccine.data.repository.Repository
import com.example.numanvaccine.data.repository.VaccinatedRepository
import com.example.numanvaccine.data.room.db.CountryDao
import com.example.numanvaccine.data.room.mappers.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providecountriesListRepository(
        dao: CountryDao,
        retrofit: VaccinatedCountriesService,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): Repository {
        return VaccinatedRepository(dao, retrofit, cacheMapper, networkMapper)
    }
}


