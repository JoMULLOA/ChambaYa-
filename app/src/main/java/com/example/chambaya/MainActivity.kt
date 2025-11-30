package com.example.chambaya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chambaya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Configurar App Bar con los fragmentos principales
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.jobListFragment)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        // Ocultar el título en la pantalla principal para un diseño más limpio
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.jobListFragment -> {
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                    binding.topAppBar.title = ""
                }
                else -> {
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
