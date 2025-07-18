package com.waffiq.mvvmexample.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.waffiq.mvvmexample.R
import com.waffiq.mvvmexample.databinding.ActivitySecondBinding
import com.waffiq.mvvmexample.domain.model.ResponseItemDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

  private lateinit var dataExtra: ResponseItemDomain

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivitySecondBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivitySecondBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbar)

    dataExtra = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      intent.getParcelableExtra(EXTRA_ID, ResponseItemDomain::class.java)
    } else {
      @Suppress("DEPRECATION")
      intent.getParcelableExtra(EXTRA_ID)
    } ?: error("No DataExtra"))

    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_second) as NavHostFragment

    val navController = navHostFragment.navController

    val navGraph = navController.navInflater.inflate(R.navigation.nav_graph_second)

    val bundle = Bundle().apply {
      putParcelable("data", dataExtra)
    }

    // set start destination and initial arguments
    navGraph.setStartDestination(R.id.First2Fragment)
    navController.setGraph(navGraph, bundle)

    appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_second)
    return navController.navigateUp(appBarConfiguration)
      || super.onSupportNavigateUp()
  }

  companion object {
    const val EXTRA_ID = "EXTRA_ID"
  }
}