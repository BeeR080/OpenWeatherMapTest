package com.example.openweathermaptest.presentation.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
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
import com.example.openweathermaptest.presentation.WeatherViewModel
import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto
import com.example.openweathermaptest.data.model.remoteDto.detail.MainDto
import com.example.openweathermaptest.data.model.remoteDto.detail.WeatherDto
import com.example.openweathermaptest.data.model.remoteDto.main.WeatherFiveDaysDto
import com.example.openweathermaptest.data.model.remoteDto.detail.WeatherListDto
import com.example.openweathermaptest.data.model.remoteDto.detail.WindDto
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
    private lateinit var locationManager:LocationManager
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
        checkGps()
        getWeather()

        return binding.root
    }


   private fun checkGps(){
    locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val gpsStatus =locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    if(gpsStatus){
      getWeather()
    }else{
        getWeatherLocal()
    }
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

          /* when(weather){
               is  ->{}
           }
*/
/*   when(weather){

        is WeatherResult.Success -> {}

        is WeatherResult.Error ->{
            getWeatherLocal()


        }

        is WeatherResult.Loading -> {
            startLoading()

        }
    }*/

        }


    }

    private fun getWeatherRemote(weather: WeatherFiveDaysDto){
        stopLoading()
        binding.mainfragTvCity.text = weather.city?.name
        val list = weather!!.list.toList()
        CoroutineScope(Dispatchers.IO).launch {
            for (i in list){
                val dbList = WeatherListLocDto(
                    id = i.dt!!,
                    date = i.dttTxt!! ,
                    humidity = i.main!!.humidity.toString(),
                    tMin = i.main.tempmin!!.toInt(),
                    tMax = i.main.tempmax!!.toInt(),
                    pressure = i.main.pressure!! ,
                    speed = i.wind!!.speed!!.toInt(),
                    tCur = i.main.temp!!.toInt(),
                    cityName = weather.city!!.name.toString() ,
                    feelsLike = i.main.feelslike!!.toInt() ,
                    visibility = i.visibility!!.toInt(),
                    description = i.weather!![0].description.toString()

                )
                weatherVM.addWeather(dbList)

            }

        }
        adapter.submitList(list)
    }

    private fun getWeatherLocal(){
        stopLoading()
        weatherVM.getLocWeather.observe(viewLifecycleOwner){ weatherLoc->

            binding.mainfragTvCity.text = weatherLoc[0].cityName
            val list = mutableListOf<WeatherListDto>()
            for (data in weatherLoc){

                list.add(
                    WeatherListDto(
                        clouds = null ,
                        dt = data.id,
                        dttTxt = data.date,
                        main = MainDto(
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
                        weather = arrayListOf<WeatherDto>(
                            WeatherDto(
                                description = data.description,
                                icon = null,
                                id = null,
                                main = null)
                        ),
                        wind = WindDto(
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

    private fun startLoading(){
        binding.mainfragCircle.visibility = View.VISIBLE
        binding.mainfragCityCard.visibility = View.GONE

    }

    private fun stopLoading(){
        binding.mainfragCircle.visibility = View.GONE
        binding.mainfragCityCard.visibility = View.VISIBLE
    }


    private fun initAdapter(){
        adapter = WeatherAdapter(object : WeatherAdapter.OnItemClick{
            override fun onClickItem(weatherList: WeatherListDto) {
                Toast.makeText(
                    requireContext(),
                    "clickOn : ${weatherList.dt}",
                    Toast.LENGTH_SHORT)
                    .show()
                val action =
                    MainWeatherFragmentDirections.actionHomeFragmentToDetailWeatherFragment(
                        weatherList
                    )
                findNavController().navigate(action)

            }


        })
        val recyclerView = binding.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }




    @SuppressLint("MissingPermission")
    private fun registerPermissons() {
        pLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) {

                    geoService.requestLocationUpdates(locationRequest,locationCallback,null)


                } else {


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
