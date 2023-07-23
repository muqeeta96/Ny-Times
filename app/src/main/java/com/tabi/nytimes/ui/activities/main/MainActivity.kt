package com.tabi.nytimes.ui.activities.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.tabi.nytimes.R
import com.tabi.nytimes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setUpNavControllerWithToolBar()
        onDestinationChangedListener()

    }

    private fun setUpNavControllerWithToolBar() {
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbarLayout.materialToolbar)
        binding.toolbarLayout.materialToolbar.setupWithNavController(
            navController,
            appBarConfiguration
        )

    }

    private fun onDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.articleFragment) {
                binding.toolbarLayout.materialToolbar.menu.setGroupVisible(R.id.timeGroupMenu, true)
            } else {
                binding.toolbarLayout.materialToolbar.menu.setGroupVisible(
                    R.id.timeGroupMenu,
                    false
                )
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.todayMenu -> {
                refreshArticles(1)
                true
            }
            R.id.weekMenu -> {
                refreshArticles(7)
                true
            }
            R.id.monthMenu -> {
                refreshArticles(30)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun refreshArticles(period: Int) {
        mainViewModel.isMenuClicked = true
        mainViewModel.refreshArticles.postValue(period)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.article_time_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }


}