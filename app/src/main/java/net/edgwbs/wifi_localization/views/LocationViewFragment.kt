package net.edgwbs.wifi_localization.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.edgwbs.wifi_localization.R
import net.edgwbs.wifi_localization.databinding.FragmentLocationViewBinding

class LocationViewFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLocationViewBinding.inflate(inflater, container, false)
        binding.settingButton.setOnClickListener {
            findNavController().navigate(R.id.action_to_setting)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                findNavController().navigate(R.id.action_to_setting)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}