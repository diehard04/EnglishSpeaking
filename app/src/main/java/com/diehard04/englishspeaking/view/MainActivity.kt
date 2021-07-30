package com.diehard04.englishspeaking.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.diehard04.englishspeaking.R
import com.diehard04.englishspeaking.databinding.ActivityMainBinding
import com.diehard04.englishspeaking.utils.ConnectivityReceiver

class MainActivity : BaseActivity(){

    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,  " onCreate")
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_grammar, R.id.navigation_vocab)
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,  " onPause")
    }
    override fun onResume() {
        super.onResume()

        Log.d(TAG,  " onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, " onRestart")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,  " onDestroy")

    }
}