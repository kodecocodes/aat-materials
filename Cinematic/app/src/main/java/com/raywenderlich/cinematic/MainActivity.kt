package com.raywenderlich.cinematic

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.raywenderlich.cinematic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
    val navController = navHostFragment.navController
    binding.bottomNav.setupWithNavController(navController)
    navController.addOnDestinationChangedListener { _, destination, _ ->

      when (destination.id) {
        R.id.movieDetailsFragment -> binding.bottomNav.visibility = View.GONE
        else -> binding.bottomNav.visibility = View.VISIBLE
      }
    }
  }
}