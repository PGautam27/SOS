package com.example.womensafetyapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.womensafetyapp.domain.dto.Contact
import com.example.womensafetyapp.modal.CurrentLocationGen
import com.example.womensafetyapp.presentation.HomeActivity
import com.example.womensafetyapp.presentation.Screen
import com.example.womensafetyapp.presentation.screens.AddPhoneNumberScreen
import com.example.womensafetyapp.presentation.screens.HomeScreen
import com.example.womensafetyapp.presentation.screens.SignUp
import com.example.womensafetyapp.presentation.screens.SingIn
import com.example.womensafetyapp.presentation.viewModel.LoginScreenViewModel
import com.example.womensafetyapp.presentation.viewModel.LoginViewModelFactory
import com.example.womensafetyapp.ui.theme.WomenSafetyAppTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val READ_CONTACTS_PERMISSION = Manifest.permission.READ_CONTACTS
        const val READ_CONTACTS_REQUEST_CODE = 42
        const val PERMISSION_SEND_SMS = 1
        const val PICK_CONTACTS_REQUEST_CODE = 1
    }
    private lateinit var loginViewModel : LoginScreenViewModel
    private lateinit var locationManager: LocationManager
    private lateinit var currentLocationGen: CurrentLocationGen


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Request location permission
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        currentLocationGen = CurrentLocationGen(locationManager,this)

        lifecycleScope.launchWhenCreated { checkContacts() }
        loginViewModel = ViewModelProvider(this,LoginViewModelFactory(application))[LoginScreenViewModel::class.java]

        setContent {
            WomenSafetyAppTheme {
                // A surface container using the 'background' color from the theme
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Button(onClick = {
//                        currentLocationGen.checkLocation()
//                    }) {
//                        Text(text = "Click me")
//                    }
//                }
                
                val navController = rememberNavController();
                NavHost(navController = navController, startDestination = Screen.HomeScreen.route ){
                    
                    composable(Screen.HomeScreen.route){
                        HomeScreen(navController)
                    }
                    composable(Screen.SignInScreen.route){
                        SingIn(navController = navController, this@MainActivity,onClick = {goToHomeActivity()})
                    }
                    composable(Screen.SignUpScreen.route){
                        SignUp(navController = navController)
                    }
                    composable(Screen.AddPhoneNumbers.route){
                        AddPhoneNumberScreen(navController = navController, loginViewModel,onClick = {pickContacts()})
                    }
                }
            }
        }
    }

    private fun goToHomeActivity(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    private fun checkContacts(){
        if (ContextCompat.checkSelfPermission(
                this,
                READ_CONTACTS_PERMISSION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_CONTACTS_PERMISSION),
                READ_CONTACTS_REQUEST_CODE
            )
        }
    }

    @SuppressLint("IntentReset")
    private fun pickContacts() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, PICK_CONTACTS_REQUEST_CODE)
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_CONTACTS_REQUEST_CODE && resultCode == RESULT_OK) {
            var contacturi = data?.data?: return
            var cols = arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
            var cursor = contentResolver.query(contacturi,cols,null,null,null)
            if (cursor?.moveToFirst()!!){
                val contact = Contact(cursor.getString(1),cursor.getString(0))
                loginViewModel.addPhoneNumber(contact)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_SEND_SMS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted

            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied to send SMS", Toast.LENGTH_SHORT).show()
            }
        }
    }
}