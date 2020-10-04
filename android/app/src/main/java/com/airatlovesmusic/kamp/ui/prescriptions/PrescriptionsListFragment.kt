package com.airatlovesmusic.kamp.ui.prescriptions

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.shared.base.BaseFragment

class PrescriptionsListFragment: BaseFragment(R.layout.fragment_prescriptions_list) {

    private val weekDay by lazy { requireArguments().getInt(ARG_WEEKDAY, 0) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_title).text = weekDay.toString()
    }

    companion object {
        fun create(weekDay: Int) = PrescriptionsFragment().apply {
            arguments = bundleOf(
                ARG_WEEKDAY to weekDay
            )
        }
        const val ARG_WEEKDAY = "WEEK_DAY"
    }

}
