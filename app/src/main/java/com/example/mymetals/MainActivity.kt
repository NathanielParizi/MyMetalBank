package com.example.mymetals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mymetals.databinding.ActivityMainBinding
import com.example.mymetals.di.appModule
import com.example.mymetals.network.MetalViewModel
import kotlinx.coroutines.delay
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin


private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private var debounced = false

    // test

    private lateinit var navigation: Navigation
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeApplication()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Android App"

        binding.navigationBottomBar.setupWithNavController(findNavController(R.id.nav_host_fragment_container))
        debounced = true

    }

    private fun initializeApplication() {
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }

    private suspend fun debounce() {
        delay(5000)
        debounced = true
    }


}
