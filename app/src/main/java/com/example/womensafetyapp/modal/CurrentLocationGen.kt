package com.example.womensafetyapp.modal

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CurrentLocationGen(locationManager: LocationManager,context: ComponentActivity) : LocationListener {

    private val REQUEST_LOCATION_PERMISSION = 1
    private val locationManager1: LocationManager = locationManager
    private val context = context
    private val PERMISSION_SEND_SMS = 1

    fun checkLocation(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION)
        } else {
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        // Check if location services are enabled
        if (!locationManager1.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "Please enable GPS", Toast.LENGTH_SHORT).show()
        } else {
            // Get the last known location
            val location = locationManager1.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            locationManager1.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                // Send the location as an SOS message
                val message = "SOS! My current location is: https://www.google.com/maps?q=${location.latitude},${location.longitude}"
                // Check if the app has permission to send SMS
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    // Request permission
                    ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.SEND_SMS), PERMISSION_SEND_SMS)
                } else {
                    // Permission already granted
                    sendSms(message, "7022931717")
                }
            } else {
                // Request location updates
                locationManager1.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
            }
        }
    }

    private fun sendSms(message: String, vararg phoneNumbers: String) {
        val smsManager = SmsManager.getDefault()
        for (phoneNumber in phoneNumbers) {
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        }
        Toast.makeText(context, "SOS message sent", Toast.LENGTH_SHORT).show()
    }

    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onLocationChanged(p0: Location) {
        TODO("Not yet implemented")
//                val message = "SOS! My current location is: https://www.google.com/maps?q=${location.latitude},${location.longitude}"
        sendSms("message", "PHONE_NUMBER_1", "PHONE_NUMBER_2", "PHONE_NUMBER_3")
//         Stop location updates after sending the message
        locationManager1.removeUpdates(this)
    }

}