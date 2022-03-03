package com.example.numanvaccine.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UrlModule {
    var baseUrl = "https://api.github.com"
//    @Provides
//    fun providesBaseUrl(): String {
//        return "https://covid-api.mmediagroup.fr/"
//    }

}