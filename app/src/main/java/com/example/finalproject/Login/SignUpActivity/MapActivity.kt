package com.example.finalproject.Login.SignUpActivity

import android.app.Activity
import android.location.Location
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
import kotlinx.android.synthetic.main.activity_map.*




class MapActivity : AppCompatActivity(),OnMapReadyCallback, GoogleMap.OnMyLocationClickListener {
    override fun onMyLocationClick(p0: Location) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)



    }


    override fun onMapReady(googleMap: GoogleMap?) {

        val egypt = LatLng(31.192735, 29.905602)

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
        googleMap.addMarker(MarkerOptions().position(egypt)
                .title("Marker Your Location"))
       // googleMap.moveCamera(CameraUpdateFactory.newLatLng(egypt))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(egypt, 12.0f))


    }


}
