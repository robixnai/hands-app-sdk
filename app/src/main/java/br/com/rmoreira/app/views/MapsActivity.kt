package br.com.rmoreira.app.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.rmoreira.app.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mVenueLngLat: String
    private lateinit var mAddress: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val extras = intent.extras

        mVenueLngLat = extras.getString(UserDataDetailActivity.EXTRA_LNG_LAT)
        mAddress = extras.getString(UserDataDetailActivity.EXTRA_ADDRESS)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val venueLngLat = mVenueLngLat.split(" ")
        val venueLngLatString = LatLng(venueLngLat[1].toDouble(), venueLngLat[0].toDouble())
        val cameraPosition = CameraPosition.Builder()
                .target(venueLngLatString)
                .zoom(16.2f)
                .tilt(90f)
                .build()

        mMap.addMarker(MarkerOptions().position(venueLngLatString).title(mAddress))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(venueLngLatString))
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        mMap.setBuildingsEnabled(true)
    }
}
