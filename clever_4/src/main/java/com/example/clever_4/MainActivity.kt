package com.example.clever_4

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.INTERNET
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.lifecycle.ViewModelProvider
import com.example.clever_4.data.AtmApi
import com.example.clever_4.data.AtmListResponse
import com.example.clever_4.data.AtmViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.clever_4.databinding.ActivityMainBinding
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val LOCATION_PERMISSION_CODE = 111


class MainActivity : AppCompatActivity(), OnMapReadyCallback {


    lateinit var atmApi:AtmApi

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        configureRetrofit()

        val viewModel = ViewModelProvider(this).get(AtmViewModel::class.java)
        viewModel.fetchAtmList(atmApi)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val gomelStartLocation = LatLng(52.4248319600289, 31.01376052054917)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gomelStartLocation, 11.0f))
        if (checkLocationPermission()) {
            mMap.isMyLocationEnabled = true
        } else {
                askLocationPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_CODE && grantResults[0]== PERMISSION_GRANTED){
            mMap.isMyLocationEnabled

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun checkLocationPermission() =
        ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED


    private fun askLocationPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_CODE)
    }
    private fun configureRetrofit(){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(" https://belarusbank.by").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        atmApi = retrofit.create(AtmApi::class.java)

    }

    }

