package com.example.womensafetyapp.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.womensafetyapp.domain.CurrentLocationGen
import com.example.womensafetyapp.presentation.screens.DashBoardScreen
import com.example.womensafetyapp.presentation.ui.theme.WomenSafetyAppTheme
import com.example.womensafetyapp.presentation.viewModel.HomeViewModel
import com.example.womensafetyapp.presentation.viewModel.HomeViewModelFactory
import com.example.womensafetyapp.presentation.viewModel.LoginScreenViewModel
import com.example.womensafetyapp.presentation.viewModel.LoginViewModelFactory

class HomeActivity : ComponentActivity() {

    private lateinit var locationManager: LocationManager
    private lateinit var currentLocationGen: CurrentLocationGen
    private val REQUEST_LOCATION_PERMISSION = 1

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent.getStringExtra("Login ID")

        Log.d("Login id homeactivity",id.toString())
        homeViewModel = ViewModelProvider(this,
            HomeViewModelFactory(application)
        )[HomeViewModel::class.java]
        homeViewModel.getContacts(id.toString())

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        currentLocationGen = CurrentLocationGen(locationManager,this,homeViewModel)
        super.onCreate(savedInstanceState)
        setContent {
            WomenSafetyAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.DashBoardScreen.route
                ) {
                    composable(Screen.DashBoardScreen.route){
                        DashBoardScreen(navController = navController,onClick = {currentLocationGen.checkLocation()})
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
