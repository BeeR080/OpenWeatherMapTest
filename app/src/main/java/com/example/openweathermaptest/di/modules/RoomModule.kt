package com.example.openweathermaptest.di.modules

import android.app.Application
import com.example.openweathermaptest.data.model.local.WeatherDao
import com.example.openweathermaptest.data.model.local.WeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providePurchasesDatabase(context: Application): WeatherDataBase {
        return WeatherDataBase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun providePurchasesDao(purchasesDatabase: WeatherDataBase): WeatherDao {
        return purchasesDatabase.weatherListLocDao()

    }
}