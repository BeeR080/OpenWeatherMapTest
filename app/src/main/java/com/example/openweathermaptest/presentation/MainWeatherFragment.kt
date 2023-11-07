package com.example.openweathermaptest.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
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
import com.example.openweathermaptest.WeatherViewModel
import com.example.openweathermaptest.data.model.remote.Main
import com.example.openweathermaptest.data.model.remote.Weather
import com.example.openweathermaptest.data.model.remote.WeatherFiveDays
import com.example.openweathermaptest.data.model.remote.WeatherList
import com.example.openweathermaptest.data.model.remote.Wind
import com.example.openweathermaptest.databinding.FragmentMainWeatherBinding
import com.example.openweathermaptest.presentation.adapter.WeatherAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer

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



    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainWeatherBinding.inflate(inflater, container, false)


        registerPermissons()
        checkLockPermissions()

        geoService.requestLocationUpdates(locationRequest,geoCallback,null)
        initAdapter()

        return binding.root
    }





    private fun getWeather(lat:Double, lon:Double){

        weatherVM.getWeather(lat = lat,lon= lon)
        weatherVM.getWeather.observe(viewLifecycleOwner){weather->
        //binding.mainfragTvCity.text = weather.data?.city?.name
        createJsonFiel(weather)
        val list = weather.data!!.list.toList()
            CoroutineScope(Dispatchers.IO).launch {
                for (i in list){
                val dbList = com.example.openweathermaptest.data.model.local.WeatherListLoc(
                    id = i.dt!!,
                    date = i.dttTxt!! ,
                    humidity = i.main!!.humidity.toString(),
                    tMin = i.main.tempmin!!.toInt(),
                    tMax = i.main.tempmax!!.toInt(),
                    pressure = i.main.pressure!! ,
                    speed = i.wind!!.speed!!.toInt()
                )
                weatherVM.addWeather(dbList)

            }

            }

            weatherVM.getLocWeather.observe(viewLifecycleOwner){ weatherLoc->
                val list = mutableListOf<WeatherList>()
                for (data in weatherLoc){
                    list.add(
                        WeatherList(
                            clouds = null ,
                            dt = data.id,
                            dttTxt = data.date,
                            main = Main(
                                feelslike = null,
                                grndlevel = null,
                                humidity = data.humidity.toInt(),
                                pressure = data.pressure,
                                sealevel = null,
                                temp = null,
                                tempkf = null,
                                tempmax = data.tMax.toDouble(),
                                tempmin = data.tMin.toDouble()

                            ),
                            pop = null,
                            sys = null,
                            visibility = null,
                            weather = null,
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



   when(weather){
        is WeatherResult.Success ->{
            //adapter.submitList(list)

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




    private fun registerPermissons() {

        pLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (it) {
                     requireActivity()
                        .getSystemService(Context.LOCATION_SERVICE) as LocationManager
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
        fastestInterval = 5000
        priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
    }

}

    private val geoCallback = object: LocationCallback(){
        override fun onLocationResult(geo: LocationResult) {
            for (location in geo.locations){
                mLocation = location
                getWeather(location.latitude,location.longitude)
                Log.d("MyLog","lat:${location.longitude}")
                Log.d("MyLog","lat:${location.latitude}")
            }
        }

    }


    private fun createJsonFiel(weather:WeatherResult<WeatherFiveDays>){

        var json = JSONObject()
        val list = WeatherFiveDays(
            city = weather.data?.city,
            cnt = weather.data?.cnt,
            cod = weather.data?.cod ,
            list = weather.data!!.list,
            message = weather.data.message,
        )
        json.put("weather",list)
        saveJson(json.toString())
    }

    private fun saveJson(jsonString:String){
        val output:Writer
        val file = createFile()
        output = BufferedWriter(FileWriter(file))
        output.write(jsonString)
        output.close()
    }

    private fun createFile(): File {
        val fileName = "weatherJson.json"
        val storageDir = File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),fileName)
        if(!storageDir!!.exists()){
            storageDir.mkdir()
        }
        return File.createTempFile(fileName,".json",storageDir)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }


}