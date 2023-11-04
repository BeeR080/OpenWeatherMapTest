package com.example.openweathermaptest.di.modules

import com.example.openweathermaptest.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModules {

    private val BASE_URL = "http://api.openweathermap.org"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor()=
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY

    }
    @Singleton
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor)=
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient)=
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit)=
        retrofit.create(WeatherApi::class.java)


}