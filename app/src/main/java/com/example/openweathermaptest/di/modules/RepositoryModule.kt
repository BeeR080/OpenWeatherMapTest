package com.example.openweathermaptest.di.modules

import com.example.openweathermaptest.data.repository.WeatherRepositoryImpl
import com.example.openweathermaptest.domain.model.repository.WeatherRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideWeatherRepositoryImpl(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

}