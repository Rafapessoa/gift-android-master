package com.fiap.giftgift.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.fiap.giftgift.ui.base.BaseFragment


class MainViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){

    private val meusFragments = ArrayList<BaseFragment>()

    override fun getItem(position: Int): Fragment {
        return meusFragments[position]

    }

    override fun getCount(): Int {
        return meusFragments.size
    }

    fun addFragment(fragment : BaseFragment) {
        meusFragments.add(fragment)
    }
}