package com.ldnhat.lesson8.overview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ldnhat.lesson8.R
import com.ldnhat.lesson8.databinding.FragmentOverviewBinding
import com.ldnhat.lesson8.network.MarsApiFilter

class OverviewFragment : Fragment() {

    private val overviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentOverviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        binding.overviewViewModel = overviewViewModel
        binding.photoGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener{
            overviewViewModel.displayPropertyDetails(it)
        })

        overviewViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, {
            if (null != it){
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                overviewViewModel.displayPropertyDetailsComplete()
            }
        })

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        overviewViewModel.updateFilter(
            when(item.itemId){
                R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
                R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
                else -> MarsApiFilter.SHOW_ALL
            }
        )
        return true
    }
}   