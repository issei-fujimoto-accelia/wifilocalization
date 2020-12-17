package net.edgwbs.wifi_localization.views

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager.NETWORK_STATE_CHANGED_ACTION
import android.net.wifi.WifiManager.SCAN_RESULTS_AVAILABLE_ACTION
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.checkSelfPermission


import androidx.lifecycle.MutableLiveData
import net.edgwbs.wifi_localization.models.WiFiInfo




abstract class BaseWifiFragment : Fragment() {
    abstract fun layoutId(): Int
    private lateinit var mBinding: ViewDataBinding
    private lateinit var wifiManager: WifiManager
    fun getBinding() = mBinding
    val wifiInfoLiveData = MutableLiveData<List<WiFiInfo>>()
    private val PERMISSIONS_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        checkPermission()
        return mBinding.root
    }


    override fun onResume() {
        super.onResume()
        context?.let {
            wifiManager = it.getSystemService(WIFI_SERVICE) as WifiManager

            val intentFilter = IntentFilter()
            intentFilter.addAction(SCAN_RESULTS_AVAILABLE_ACTION)
            it.registerReceiver(wifiScanReceiver, intentFilter)
        }
    }


    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(wifiScanReceiver)
    }


    fun setScanResult() {
        Log.d("debug", "setScanResult")
        Log.d("debug", wifiManager.wifiState.toString())

        if (wifiManager.wifiState == WifiManager.WIFI_STATE_ENABLED) {
            val res = wifiManager.scanResults.map { r ->
                Log.d("debug", r.SSID)
                WiFiInfo(r.SSID, r.capabilities, r.frequency, r.level, r.timestamp)
            }
            Log.d("debug", res.toString())
            wifiInfoLiveData.postValue(res)
        }
    }

    private fun checkPermission() {
        context?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(it, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(it, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.ACCESS_NETWORK_STATE),
                        PERMISSIONS_REQUEST_CODE)
                }
            }
        }
    }

    private val wifiScanReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context, intent: Intent) {
            Log.d("debug", "onReceive  !!!!!!")
            setScanResult()
        }
    }
}