package com.example.openweathermaptest.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openweathermaptest.WeatherResult
import com.example.openweathermaptest.viewmodel.WeatherViewModel
import com.example.openweathermaptest.data.model.local.WeatherListLoc
import com.example.openweathermaptest.data.model.remote.Main
import com.example.openweathermaptest.data.model.remote.Weather

import com.example.openweathermaptest.data.model.remote.WeatherList
import com.example.openweathermaptest.data.model.remote.Wind
import com.example.openweathermaptest.databinding.FragmentMainWeatherBinding
import com.example.openweathermaptest.presentation.adapter.WeatherAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainWeatherFragment : Fragment() {

    lateinit var adapter: WeatherAdapter
    private var _binding : FragmentMainWeatherBinding? = null
    private val binding get() = _binding!!
    private val weatherVM by viewModels<WeatherViewModel>()
    private val geoService by lazy{LocationServices.getFusedLocationProviderClient(requireContext())}
    private val locationRequest by lazy{initLocationRequest()}
    private lateinit var mLocation:Location
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainWeatherBinding.inflate(inflater, container, false)


        registerPermissons()
        checkLockPermissions()
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(requireContext())

        initAdapter()
        getWeather()

        return binding.root
    }







    private fun getWeather(){
        locationCallback = object: LocationCallback(){
            override fun onLocationResult(geo: LocationResult) {
                for (location in geo.locations){
                    weatherVM.getWeather(lat = location.latitude ,lon= location.longitude)
                }
            }
        }

        weatherVM.getWeather.observe(viewLifecycleOwner){weather->

   when(weather){

        is WeatherResult.Success ->{
            stopLoading()
            binding.mainfragTvCity.text = weather.data?.city?.name
            val list = weather.data!!.list.toList()
            CoroutineScope(Dispatchers.IO).launch {
                for (i in list){
                    val dbList = WeatherListLoc(
                        id = i.dt!!,
                        date = i.dttTxt!! ,
                        humidity = i.main!!.humidity.toString(),
                        tMin = i.main.tempmin!!.toInt(),
                        tMax = i.main.tempmax!!.toInt(),
                        pressure = i.main.pressure!! ,
                        speed = i.wind!!.speed!!.toInt(),
                        tCur = i.main.temp!!.toInt(),
                        cityName = weather.data!!.city!!.name.toString() ,
                        feelsLike = i.main.feelslike!!.toInt() ,
                        visibility = i.visibility!!.toInt(),
                        description = i.weather!![0].description.toString()

                    )
                    weatherVM.addWeather(dbList)

                }

            }
            adapter.submitList(list)

        }

        is WeatherResult.Error ->{
            stopLoading()

            weatherVM.getLocWeather.observe(viewLifecycleOwner){ weatherLoc->
                val list = mutableListOf<WeatherList>()
                for (data in weatherLoc){
                    binding.mainfragTvCity.text = data.cityName
                    list.add(
                        WeatherList(
                            clouds = null ,
                            dt = data.id,
                            dttTxt = data.date,
                            main = Main(
                                feelslike = data.feelsLike.toDouble(),
                                grndlevel = null,
                                humidity = data.humidity.toInt(),
                                pressure = data.pressure,
                                sealevel = null,
                                temp = data.tCur.toDouble(),
                                tempkf = null,
                                tempmax = data.tMax.toDouble(),
                                tempmin = data.tMin.toDouble()

                            ),
                            pop = null,
                            sys = null,
                            visibility = data.visibility,
                            weather = arrayListOf<Weather>(
                                Weather(
                                description = data.description,
                                icon = null,
                                id = null,
                                main = null)
                            ),
                            wind = Wind(
                                deg = null,
                                gust = null,
                                speed = data.speed.toDouble()
                            ),
                        )


                    )
                    adapter.submitList(list)
                }
            }
        }

        is WeatherResult.Loading -> {
            startLoading()

        }
    }

        }

    }

    private fun startLoading(){
        binding.mainfragCircle.visibility = View.VISIBLE
        binding.mainfragCityCard.visibility = View.GONE

    }

    private fun stopLoading(){
        binding.mainfragCircle.visibility = View.GONE
        binding.mainfragCityCard.visibility = View.VISIBLE
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




    private fun registerPermissons() {
        pLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) {
                    geoService.requestLocationUpdates(locationRequest,locationCallback,null)


                } else {
                    checkLockPermissions()

                }
            }

    }




    private fun checkLockPermissions(){

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission_group.LOCATION)
            -> {

            }
            else -> {
                pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

    }




    private fun initLocationRequest():com.google.android.gms.location.LocationRequest{

        val request = com.google.android.gms.location.LocationRequest.create()

        return request.apply {
        interval = 10000
        fastestInterval = 3000
        priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
    }

}








    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }



}
