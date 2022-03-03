package com.example.numanvaccine.presentation.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.numanvaccine.R
import com.example.numanvaccine.databinding.FragmentDetailedBinding
import com.example.numanvaccine.domain.model.Country
import com.example.numanvaccine.domain.model.CountryCodes
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedFragment : Fragment(R.layout.fragment_detailed), OnMapReadyCallback {
    private var mapFragment: SupportMapFragment? = null
    private var mMap: GoogleMap? = null
    private lateinit var countryData: Country
    private var _binding: FragmentDetailedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = getChildFragmentManager().findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        countryData = getArguments()?.getSerializable("country") as Country
        binding.countryInfo.populationView.text = countryData.population.toString()
        binding.countryInfo.regionView.text = countryData.continent
        binding.countryInfo.countryView.text = countryData.country
        binding.countryInfo.areaView.text = countryData.sqKmArea.toString()
        binding.countryInfo.administeredView.text = countryData.administered.toString()
        binding.countryInfo.peopleVaccinatedView.text = countryData.peopleVaccinated.toString()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap!!
        val country = CountryCodes.getCountryCode(countryData.country)
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(country.lat, country.lon), 5f));
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mMap != null) {
            val fragmentManager = activity?.supportFragmentManager
            fragmentManager?.findFragmentById(R.id.map)?.let {
                fragmentManager
                    ?.beginTransaction()
                    ?.remove(it).commit()
            }
            mMap = null
        }
    }
}
