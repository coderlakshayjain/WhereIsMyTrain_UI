package com.example.whereismytraindemo

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3  // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentTab1()
            1 -> FragmentTab2()
            2 -> FragmentTab3()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}