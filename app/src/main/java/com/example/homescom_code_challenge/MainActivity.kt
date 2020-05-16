package com.example.homescom_code_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tmdbAPI = BuildConfig.TMDB_API_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupNavMenu(navController)
    }

    private fun setupNavMenu(navController: NavController) {
        nav_menu?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }
}
