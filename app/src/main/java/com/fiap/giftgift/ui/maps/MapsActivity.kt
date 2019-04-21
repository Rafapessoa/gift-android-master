package com.fiap.giftgift.ui.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fiap.giftgift.R
import com.fiap.giftgift.utils.permissionUtils


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    fun initLocationListener() {
        locationListener = object: LocationListener{
            override fun onLocationChanged(location: Location?) {
                val minhaLocalizacao = LatLng(location?.latitude!!, location?.longitude)
                mMap.addMarker(MarkerOptions().position(minhaLocalizacao))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minhaLocalizacao,15f))

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        permissionUtils.validarPermissoes(
                listOf(Manifest.permission.ACCESS_FINE_LOCATION), this,1)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for(permissao in grantResults){
            if(permissao == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this,"Permissão negada",Toast.LENGTH_SHORT).show()
            } else{

            }
        }
    }

    @SuppressLint("MissingPermission")
    fun requestLoationUpdates(){
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0f, locationListener
        )
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        initLocationListener()
        requestLoationUpdates()

        mMap.setOnMapClickListener {
            mMap.addMarker(MarkerOptions().position(it))
        }

        mMap.setOnMapLongClickListener {
            mMap.addMarker(MarkerOptions().position(it))
        }

        // Add a marker in Sydney and move the camera
        val fiapPaulista = LatLng(-23.5565804,-46.662113)
        mMap.addMarker(MarkerOptions()
                .position(fiapPaulista)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)).
                title("Fiap - Campus Paulista"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiapPaulista,15f))
        adicionarFormaCircular(fiapPaulista)
    }

    private fun adicionarFormaCircular(latLng: LatLng){
        val circulo = CircleOptions()
        circulo.center(latLng)
        circulo.radius(200.0)
        circulo.fillColor(Color.argb(128,0,51,102))
        circulo.strokeColor(Color.argb(128,0,51, 102))
        mMap.addCircle(circulo)
    }

}
