package com.example.openweathermaptest.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openweathermaptest.WeatherResult
import com.example.openweathermaptest.WeatherViewModel
import com.example.openweathermaptest.data.model.WeatherFiveDays
import com.example.openweathermaptest.data.model.WeatherList
import com.example.openweathermaptest.databinding.FragmentMainWeatherBinding
import com.example.openweathermaptest.presentation.adapter.WeatherAdapter
import com.example.openweathermaptest.utills.DateUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainWeatherFragment : Fragment() {

    lateinit var adapter: WeatherAdapter
    private var _binding : FragmentMainWeatherBinding? = null
    private val binding get() = _binding!!
    private val weatherVM by viewModels<WeatherViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainWeatherBinding.inflate(inflater, container, false)
        initAdapter()
        getWeather(	55.7522,37.6156)

        return binding.root
    }




    private fun getWeather(lat:Double,lon:Double){
        weatherVM.getWeather(lat = lat,lon= lon)
        weatherVM.getWeather.observe(viewLifecycleOwner){weather->
        val list = weather.data!!.list.toList()



   when(weather){

        is WeatherResult.Success ->{
            adapter.submitList(list)


        }

        is WeatherResult.Error ->{
            Log.d("MyLog",weather.message.toString())
        }

        is WeatherResult.Loading -> {}
    }

        }

    }


    private fun initAdapter(){
        adapter = WeatherAdapter(object :WeatherAdapter.OnItemClick{
            override fun onClickItem(weatherList: WeatherList) {
                Toast.makeText(
                    requireContext(),
                    "clickOn : ${weatherList.dt}",
                    Toast.LENGTH_SHORT)
                    .show()
                val action =
                    MainWeatherFragmentDirections
                        .actionHomeFragmentToDetailWeatherFragment(weatherList)
                findNavController().navigate(action)

            }


        })
        val recyclerView = binding.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }



}