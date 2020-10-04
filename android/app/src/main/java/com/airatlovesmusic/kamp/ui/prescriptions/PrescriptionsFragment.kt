package com.airatlovesmusic.kamp.ui.prescriptions

import android.os.Bundle
import android.view.View
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.kamp.databinding.FragmentPrescriptionsBinding
import com.airatlovesmusic.shared.base.BaseFragment

class PrescriptionsFragment: BaseFragment(R.layout.fragment_prescriptions) {

    lateinit var binding: FragmentPrescriptionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrescriptionsBinding.bind(view)
        initViewPager()
    }

    private fun initViewPager() {
        with(binding.vpDays) {
            adapter = object : FragmentStateAdapter(this@PrescriptionsFragment) {
                override fun getItemCount() = 7
                override fun createFragment(pos: Int) =
                    PrescriptionsListFragment.create(pos)
            }
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                }
            })
        }
    }

}