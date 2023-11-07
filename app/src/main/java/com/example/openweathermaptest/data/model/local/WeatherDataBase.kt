package com.example.openweathermaptest.data.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(
    entities = [WeatherListLoc::class],
    version = 1,
    exportSchema = false )


abstract class WeatherDataBase():RoomDatabase() {
    abstract fun  weatherListLocDao(): WeatherDao

    companion object{
        @Volatile
        private var INSTANCE: WeatherDataBase? = null

        fun getDatabase(context: Context): WeatherDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDataBase::class.java,
                    "purchases_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
}
    }
}