package com.yara.juststudioapp.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yara.juststudioapp.ui.profile.inner.ProfileChangepasswordFragment
import com.yara.juststudioapp.ui.profile.inner.ProfileFeedbackFragment
import com.yara.juststudioapp.ui.profile.inner.ProfileMembershipsFragment
import com.yara.juststudioapp.ui.profile.inner.ProfileNotificationsFragment
import com.yara.juststudioapp.ui.profile.inner.ProfileRecordsFragment
import com.yara.juststudioapp.ui.profile.inner.ProfileSettingsFragment

class VPAdapter(fragmentActivity: ProfileFragment) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileSettingsFragment()
            1 -> ProfileMembershipsFragment()
            2 -> ProfileNotificationsFragment()
            3 -> ProfileRecordsFragment()
            4 -> ProfileChangepasswordFragment()
            else -> ProfileFeedbackFragment()
        }
    }
}