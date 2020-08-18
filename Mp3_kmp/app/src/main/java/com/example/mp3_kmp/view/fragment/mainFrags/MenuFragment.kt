package com.example.mp3_kmp.view.fragment.mainFrags


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2

import com.example.mp3_kmp.R
import com.example.mp3_kmp.view.adapter.MenuPageAdapter
import com.example.mp3_kmp.view.fragment.AlbumListFragment
import com.example.mp3_kmp.view.fragment.ArtistListFragment
import com.example.mp3_kmp.view.fragment.FaixaListFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment() {
    //VARIABLES
    lateinit var  menuPageAdapter : MenuPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuPageAdapter = MenuPageAdapter(this@MenuFragment.activity!!)

        initView()

    }

    fun initView() {
        // SETTING TAB AND PAGER SYNC
        menu_frag_pager.adapter = menuPageAdapter
        setTabLayoutMediator(menu_frag_tabLayout, menu_frag_pager, menuPageAdapter)

    }
    private fun setTabLayoutMediator(tabLayout: TabLayout,
                                     viewPager2: ViewPager2,
                                     pageAdapter: MenuPageAdapter
    ){
        TabLayoutMediator(tabLayout, viewPager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->

                tab.icon = resources.getDrawable(pageAdapter.getIcon(position))

            }

        ).attach()
    }

}