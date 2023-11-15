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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openweathermaptest.presentation.WeatherViewModel
import com.example.openweathermaptest.databinding.FragmentMainWeatherBinding
import com.example.openweathermaptest.domain.model.remote.WeatherFiveDays
import com.example.openweathermaptest.domain.model.remote.WeatherList
import com.example.openweathermaptest.presentation.adapter.WeatherAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

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

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(requireContext())

        initAdapter()
        checkGps()

        return binding.root
    }




   private fun checkGps(){

    locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    if(gpsStatus){
        registerPermissons()
        checkLockPermissions()
        getWeather()

}else{
    getWeatherLocal()
}
}

private fun getWeather(){
    startLoading()
    locationCallback = object: LocationCallback(){
        override fun onLocationResult(geo: LocationResult) {
            for (location in geo.locations){
                weatherVM.getWeather(lat = location.latitude ,lon= location.longitude)
            }
        }
    }
    weatherVM.getWeather.observe(viewLifecycleOwner){weather->
        getWeatherRemote(weather!!)

    }


}

private fun getWeatherRemote(weather: WeatherFiveDays){
    stopLoading()
    binding.mainfragTvCity.text = weather.city?.name
    adapter.submitList(weather.list)
    lifecycleScope.launch {
            weatherVM.addWeather(weather)

    }



}

private fun getWeatherLocal(){

    weatherVM.getWeatherLoc()
    weatherVM.getLocWeather.observe(viewLifecycleOwner){ weatherLoc->

        adapter.submitList(weatherLoc)
        stopLoading()
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
            override fun onClickItem(weatherList: WeatherList) {
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
