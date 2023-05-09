package com.example.womensafetyapp.presentation

sealed class Screen(val route:String) {
    object HomeScreen : Screen("home_screen")
    object SignUpScreen : Screen("signup_screen")
    object SignInScreen : Screen("signin_screen")
    object AddPhoneNumbers : Screen("add_phone_number")
}