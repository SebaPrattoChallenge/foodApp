package com.sebapp.challenge.presentation.maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStateAtLeast
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdate

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import com.sebapp.challenge.R
import com.sebapp.challenge.presentation.foodDetail.DetailFragmentArgs
import kotlinx.coroutines.launch

class MapsFragment : Fragment() {

    private lateinit var googleMap: GoogleMap
    private val args: MapsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        lifecycleScope.launch {
            lifecycle.whenStateAtLeast(State.CREATED) {
                googleMap = mapFragment?.awaitMap()!!
                setMaps(googleMap)
            }
        }
    }

    private fun setMaps(googleMap: GoogleMap) {
        val address = LatLng(
            args.food.positionLat.toDouble(),
            args.food.positionLong.toDouble()
        )
        val market = googleMap.addMarker {
            position(address)
            title(args.food.name)
        }
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(address,10f),
            3000,
            null
        )
    }
}