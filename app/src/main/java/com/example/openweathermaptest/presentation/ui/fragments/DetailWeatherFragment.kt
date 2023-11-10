package com.example.openweathermaptest.presentation.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.openweathermaptest.R
import com.example.openweathermaptest.databinding.FragmentDetailWeatherBinding
import com.example.openweathermaptest.utills.DateUtils


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


    @SuppressLint("SetTextI18n")
    private fun getDetailWeather()= with(binding){
            detailFragTvDate.text = " ${DateUtils.dateFormat(args.currentWeather.dttTxt)}"
            detailFragTvDescription.text = " ${args.currentWeather.weather?.get(0)?.description}"
            detailFragTvPressure.text = resources
                .getString(R.string.pressure)+ " ${args.currentWeather.main?.pressure.toString()} гПа"
            detailFragTvHumidity.text = resources
                .getString(R.string.humidity)+ " ${args.currentWeather.main?.humidity.toString()} %"
            detailFragTvTmax.text = resources.
            getString(R.string.maxt)+ " ${args.currentWeather.main?.tempmax.toString()} °C"
            detailFragTvTmin.text = resources
                .getString(R.string.mint) + " ${args.currentWeather.main?.tempmin.toString()} °C"
            detailFragTvWindSpeed.text = resources
                .getString(R.string.wendspeed)+ " ${args.currentWeather.wind?.speed.toString()} м/c"
            detailFragTvT.text =
                resources.getString(R.string.tnow)+ " ${args.currentWeather.main?.temp.toString()} °C"
            detailFragTvTFeelsLike.text =
                resources.getString(R.string.tfeelslike)+ " ${args.currentWeather.main!!.feelslike.toString()} °C"
            detailFragTvVisibility.text =
                resources.getString(R.string.visibility)+ " ${args.currentWeather.visibility.toString()} м"
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}