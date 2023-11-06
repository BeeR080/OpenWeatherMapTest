package com.example.openweathermaptest.utills


import java.text.SimpleDateFormat


object DateUtils {

    fun dateFormat(date:String?):String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val newSimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val date = simpleDateFormat.parse(date)
        return newSimpleDateFormat.format(date)
    }
}