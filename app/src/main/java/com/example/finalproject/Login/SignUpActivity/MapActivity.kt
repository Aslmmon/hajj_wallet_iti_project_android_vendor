package com.example.finalproject.Login.SignUpActivity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng




class MapActivity : AppCompatActivity(),OnMapReadyCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)



    }

    override fun onMapReady(googleMap: GoogleMap?) {

        val egypt = LatLng(26.8206, 30.8025)

        googleMap!!.isMyLocationEnabled
        googleMap.setOnMapClickListener {
            Toast.makeText(this,"location is + ${it.latitude.toString()} ",Toast.LENGTH_SHORT).show()
            val returnIntent = intent
            returnIntent.putExtra("LatLng", it)
            returnIntent.putExtra("Latitude", it.latitude)
            returnIntent.putExtra("Longtitude", it.longitude)
            Log.i("Location","Your Location in MapActivity is ${it}")
            setResult(RESULT_OK, returnIntent)
            finish()

        }
        googleMap!!.addMarker(MarkerOptions().position(egypt)
                .title("Marker in Egypt"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(egypt))

    }
}
