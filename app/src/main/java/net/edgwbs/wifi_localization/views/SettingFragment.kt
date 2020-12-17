package net.edgwbs.wifi_localization.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import net.edgwbs.wifi_localization.R
import net.edgwbs.wifi_localization.databinding.FragmentSettingBinding
import net.edgwbs.wifi_localization.models.WiFiInfo
import net.edgwbs.wifi_localization.views.adapter.WiFiInfoAdapter


class SettingFragment : BaseWifiFragment() {
    override fun layoutId() = R.layout.fragment_setting
    private lateinit var binding: FragmentSettingBinding
    private lateinit var adapter: WiFiInfoAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = getBinding() as FragmentSettingBinding
        val wifiInfo = WiFiInfo("test bssid", "test capabilities", -1,-1,-1)

        binding.wifiInfo = wifiInfo.viewFormat()

        adapter = WiFiInfoAdapter()
        binding.wifiList.adapter = adapter

        setObserver()
        setButton()

        return binding.root
    }

    private fun setButton() {
        binding.loadWifiButton.setOnClickListener {
            setScanResult()
        }
    }
    private fun setObserver() {
        wifiInfoLiveData.observe(
            viewLifecycleOwner,
            Observer {
                Log.d("eeeee", "set observer!!!!!!!!!!")
                adapter.setList(it)
            }
        )
    }
}