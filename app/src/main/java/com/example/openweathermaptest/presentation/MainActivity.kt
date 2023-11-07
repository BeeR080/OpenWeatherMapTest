package com.example.openweathermaptest.presentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.openweathermaptest.R
import com.example.openweathermaptest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var navController:NavController





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bottomNav = binding.bottomNav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNav,navController)
setup(bottomNav)
        //bottomNav.setupWithNavController(navController = navController)

    }

    private fun setup(bottomNavigation:BottomNavigationView){
        bottomNavigation.setOnItemSelectedListener(object :BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.one_dest->findNavController(R.id.navHostFragment).navigate(R.id.emptyFragment)
                    R.id.two_dest->findNavController(R.id.navHostFragment).navigate(R.id.emptyFragment)
                    R.id.four_dest->findNavController(R.id.navHostFragment).navigate(R.id.emptyFragment)
                    R.id.three_dest->findNavController(R.id.navHostFragment).navigate(R.id.emptyFragment)
                    R.id.homeFragment->findNavController(R.id.navHostFragment).navigate(R.id.homeFragment)

                }

                return true } })

    }







}