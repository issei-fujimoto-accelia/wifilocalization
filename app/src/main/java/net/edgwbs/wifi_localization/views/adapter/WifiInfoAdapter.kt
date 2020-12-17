package net.edgwbs.wifi_localization.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.edgwbs.wifi_localization.R
import net.edgwbs.wifi_localization.databinding.WifiInfoBinding
import net.edgwbs.wifi_localization.models.WiFiInfo

class WiFiInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class WiFiInfoViewHolder(val binding: WifiInfoBinding) : RecyclerView.ViewHolder(binding.root)

    private var wifiInfoList: MutableList<WiFiInfo> = mutableListOf()

    override fun getItemCount(): Int = wifiInfoList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val h = holder as WiFiInfoViewHolder
        h.binding.wifiInfo = wifiInfoList[position].viewFormat()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: WifiInfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.wifi_info, parent, false
        )

        return WiFiInfoViewHolder(binding)
    }

    fun setList(li: List<WiFiInfo>) {
        Log.d("ee", "set notifyDataSetChanged")
        wifiInfoList.addAll(li)
        notifyDataSetChanged()
    }
}