package com.example.mp3_kmp.view.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mp3_kmp.logic.FragmentsInstances


class MenuPageAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity){

    private val pageFragmentList = FragmentsInstances.fragPages
    private val iconList = FragmentsInstances.icons

    fun getIcon(position: Int) = iconList[position]
    override fun getItemCount() = pageFragmentList.size
    override fun createFragment(position: Int) = pageFragmentList[position]


}
