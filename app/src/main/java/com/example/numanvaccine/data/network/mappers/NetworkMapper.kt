package com.example.numanvaccine.data.network.mappers

import android.util.Log
import com.example.numanvaccine.data.network.model.CountryDto
import com.example.numanvaccine.domain.model.Country
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : DomainMapper<CountryDto, Country> {

    override fun mapFromData(entity: CountryDto): Country {
        return Country(
            id = 0,
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
            favourite = 0
        )
    }

    override fun mapToData(domainModel: Country): CountryDto {
        return CountryDto(
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
            updated = domainModel.updated
        )
    }

    override fun mapFromDataList(entities: List<CountryDto>): List<Country> {
        return entities.map { mapFromData(it) }
    }

}
