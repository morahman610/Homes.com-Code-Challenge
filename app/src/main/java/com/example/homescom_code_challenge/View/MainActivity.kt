package com.example.homescom_code_challenge.View

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.homescom_code_challenge.BuildConfig
import com.example.homescom_code_challenge.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val tmdbAPI = BuildConfig.TMDB_API_KEY
    private val MY_PERMISSIONS_REQUEST = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this,
            R.id.nav_host_fragment
        )


        setupNavMenu(navController)
    }

    private fun setupNavMenu(navController: NavController) {
        val sharedPrefs = this.getSharedPreferences("HOMES_SHARED_PREFS", Context.MODE_PRIVATE)
        val lastFeature = sharedPrefs.getString("LAST_FEATURE", "PopularMoviesFragment")

        nav_menu?.let {
            NavigationUI.setupWithNavController(it, navController)
        }

        if (lastFeature == "PopularTVShowsFragment") {
            val navHostFragment = nav_host_fragment as NavHostFragment
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.nav_graph)
            graph.startDestination = R.id.destination_popular_tv
            navHostFragment.navController.graph = graph
        }

    }

    private fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST
            )
        }
    }
}
