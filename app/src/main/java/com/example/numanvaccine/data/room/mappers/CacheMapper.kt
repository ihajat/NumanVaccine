package com.example.numanvaccine.data.room.mappers;

import com.example.numanvaccine.data.network.mappers.DomainMapper
import com.example.numanvaccine.data.room.model.CountryEntity
import com.example.numanvaccine.domain.model.Country
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : DomainMapper<CountryEntity, Country> {

    override fun mapFromData(entity: CountryEntity): Country {
        return Country(
            id = entity.id,
            administered = entity.administered,
            peopleVaccinated = entity.peopleVaccinated,
            peoplePartiallyVaccinated = entity.peoplePartiallyVaccinated,
            country = entity.country,
            population = entity.population,
            sqKmArea = entity.sqKmArea,
            lifeExpectancy = entity.lifeExpectancy,
            elevationInMeters = entity.elevationInMeters,
            continent = entity.continent,
            abbreviation = entity.abbreviation,
            location = entity.location,
            iso = entity.iso,
            capitalCity = entity.capitalCity,
            updated = entity.updated,
            favourite = entity.favourite
        )
    }

    override fun mapToData(domainModel: Country): CountryEntity {
        return CountryEntity(
            id = domainModel.id,
            administered = domainModel.administered,
            peopleVaccinated = domainModel.peopleVaccinated,
            peoplePartiallyVaccinated = domainModel.peoplePartiallyVaccinated,
            country = domainModel.country,
            population = domainModel.population,
            sqKmArea = domainModel.sqKmArea,
            lifeExpectancy = domainModel.lifeExpectancy,
            elevationInMeters = domainModel.elevationInMeters,
            continent = domainModel.continent,
            abbreviation = domainModel.abbreviation,
            location = domainModel.location,
            iso = domainModel.iso,
            capitalCity = domainModel.capitalCity,
            updated = domainModel.updated,
            favourite = domainModel.favourite
        )
    }

    override fun mapFromDataList(entities: List<CountryEntity>): List<Country> {
        return entities.map { mapFromData(it) }
    }
}


