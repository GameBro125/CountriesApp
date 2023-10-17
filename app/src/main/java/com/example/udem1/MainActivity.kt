package com.example.udem1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.udem1.databinding.ActivityMainBinding
import com.example.udem1.utilities.correctNumber
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener() {
            val countryName = binding.countyNameEditText.text.toString().trim()
            Toast.makeText(this, "Поиск: $countryName", Toast.LENGTH_LONG).show()

            lifecycleScope.launch {
                    val countries = restCountriesApi.getCountryByName(countryName)
                    val country = countries[0]
                    binding.countyNameTextView.text = country.name.common.toString()
                    binding.capitalNameTextView.text = country.capital[0].toString()
                    binding.populationTextView.text = correctNumber( country.population ) + " человек."
                    binding.areaTextView.text = correctNumber( country.area.toLong() ) + " кв.км."
                    binding.regionTextView.text = country.region.toString()
                    loadSvg(binding.countryFlagImageview, country.flags.svg)
                    binding.searchLayout.isVisible = false
                    binding.resultLayout.isVisible = true
            }
        }
    }
}