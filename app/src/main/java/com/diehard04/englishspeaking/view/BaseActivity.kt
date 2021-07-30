package com.diehard04.englishspeaking.view

import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.diehard04.englishspeaking.utils.ConnectivityReceiver
import com.google.android.material.snackbar.Snackbar

/**
 * Created by DieHard_04 on 30-07-2021.
 */
open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(ConnectivityReceiver(), filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(ConnectivityReceiver())
    }
    override fun onPause() {
        super.onPause()

    }
    fun showSneakBar(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null)
        snackBar.setActionTextColor(Color.BLUE)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.CYAN)
        val textView =
            snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.BLUE)
        snackBar.show()
    }

    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            val messageToUser = "You are offline now."
            Toast.makeText(this, "You are offline now", Toast.LENGTH_SHORT).show()
//            mSnackBar = Snackbar.make(findViewById(R.id.rootLayout), messageToUser, Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
//            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
//            mSnackBar?.show()
        } else {
            Toast.makeText(this, "You are online", Toast.LENGTH_SHORT).show()
            //mSnackBar?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    /**
     * Callback will be called when there is change
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}