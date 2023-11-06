package com.example.openweathermaptest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.openweathermaptest.data.model.WeatherList
import com.example.openweathermaptest.databinding.FragmentDetailWeatherBinding



class DetailWeatherFragment : Fragment() {
    private var _binding: FragmentDetailWeatherBinding? = null
    private val binding get() = _binding!!
   private val args  by navArgs<DetailWeatherFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailWeatherBinding.inflate(inflater, container, false)

getDetailWeather()

        return binding.root
    }


    private fun getDetailWeather()= with(binding){
        detailFragTvDate.text = args.currentWeather.dttTxt
            detailFragTvDescription.text = args.currentWeather.weather[0].description
            detailFragTvPressure.text = args.currentWeather.main?.pressure.toString()
            detailFragTvHumidity.text = args.currentWeather.main?.humidity.toString()
            detailFragTvTmax.text = args.currentWeather.main?.tempmax.toString()
            detailFragTvTmin.text = args.currentWeather.main?.tempmin.toString()
            detailFragTvWindSpeed.text = args.currentWeather.wind?.speed.toString()
            detailFragTvT.text = args.currentWeather.main?.temp.toString()
            detailFragTvTFeelsLike.text = args.currentWeather.main!!.feelslike.toString()
            detailFragTvVisibility.text = args.currentWeather.visibility.toString()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}