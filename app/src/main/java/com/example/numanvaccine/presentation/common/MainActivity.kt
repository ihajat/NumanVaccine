package com.example.numanvaccine.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.numanvaccine.R
import com.example.numanvaccine.domain.model.CountryCode
import com.example.numanvaccine.domain.model.CountryCodes
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.titleFragment,
                    R.id.countriesFragment,
                    R.id.favouritesFragment
                )
            )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

        gettingItemsFromCSV()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return NavigationUI.navigateUp(navController, AppBarConfiguration(navController.graph))
    }

    private fun gettingItemsFromCSV() {
        val bufferedInputStream =
            BufferedInputStream(resources.openRawResource(R.raw.latlons))
        val bufferedReader = BufferedReader(
            InputStreamReader(bufferedInputStream)
        )
        try {
            var line: String
            while (bufferedReader.readLine().also { line = it } != null) {
                val array = line.split(',')
                val code = CountryCode(
                    name = array.get(3),
                    code = array.get(0),
                    lat = array.get(1).toDouble(),
                    lon = array.get(2).toDouble()
                )
                CountryCodes.map.put(array.get(3), code)

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
