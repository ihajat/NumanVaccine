package com.example.numanvaccine.data.repository

import android.util.Log
import com.example.numanvaccine.data.network.mappers.NetworkMapper
import com.example.numanvaccine.data.network.services.VaccinatedCountriesService
import com.example.numanvaccine.data.room.db.CountryDao
import com.example.numanvaccine.data.room.mappers.CacheMapper
import com.example.numanvaccine.domain.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VaccinatedRepository
@Inject constructor(
    private val collectionDao: CountryDao,
    private val vaccinatedCountriesService: VaccinatedCountriesService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) : Repository {
    override suspend fun getCountries(): List<Country> {
        if (collectionDao.getAll().isEmpty()) {
            val networkCountries = vaccinatedCountriesService.getCountries()
            val collections = networkMapper.mapFromDataList(networkCountries.alls)
            for (collection in collections) {
                collectionDao.insert(cacheMapper.mapToData(collection))
            }
        }
        return cacheMapper.mapFromDataList(collectionDao.getAll())
    }

    override suspend fun Update(country: Country) {
        collectionDao.update(cacheMapper.mapToData(country))
    }

    override suspend fun getFavouriteList(): List<Country> {
        return cacheMapper.mapFromDataList(collectionDao.getFavourites())
    }
}