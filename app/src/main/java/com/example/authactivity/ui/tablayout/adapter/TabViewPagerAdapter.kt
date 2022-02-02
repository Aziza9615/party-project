package com.example.authactivity.ui.tablayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.authactivity.ui.tablayout.fragment.AcceptFragment
import com.example.authactivity.ui.tablayout.fragment.GiveFragment

class TabViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                AcceptFragment()
            }
            1->{
                GiveFragment()
            }
            else->{
                Fragment()
            }

        }
    }
}