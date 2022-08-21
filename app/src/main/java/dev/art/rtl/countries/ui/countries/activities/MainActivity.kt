package dev.art.rtl.countries.ui.countries.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dev.art.rtl.countries.R
import dev.art.rtl.countries.databinding.ActivityMainBinding
import dev.art.rtl.countries.ui.countries.fragments.CountriesFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CountriesFragment()).commit()
    }
}