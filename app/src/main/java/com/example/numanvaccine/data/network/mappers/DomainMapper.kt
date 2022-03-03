package com.example.numanvaccine.data.network.mappers

interface DomainMapper<Data, DomainModel> {
    fun mapFromData(entity: Data): DomainModel
    fun mapToData(domainModel: DomainModel): Data
    fun mapFromDataList(entities: List<Data>): List<DomainModel>
}