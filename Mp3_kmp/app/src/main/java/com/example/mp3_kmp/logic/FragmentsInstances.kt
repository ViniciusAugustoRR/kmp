package com.example.mp3_kmp.logic


import com.example.mp3_kmp.R
import com.example.mp3_kmp.view.fragment.*
import com.example.mp3_kmp.view.fragment.mainFrags.AlbumDetailsFragment
import com.example.mp3_kmp.view.fragment.mainFrags.ArtistDetailsFragment
import com.example.mp3_kmp.view.fragment.mainFrags.MenuFragment

object FragmentsInstances {

    val mainFrags = arrayListOf(
        MenuFragment(),
        AlbumDetailsFragment(),
        ArtistDetailsFragment()
    )


    val fragPages = arrayListOf(
            FaixaListFragment(),
            AlbumListFragment(),
            ArtistListFragment()
    )

    val icons = arrayListOf(
            R.drawable.ic_faixas,
            R.drawable.ic_album,
            R.drawable.ic_artistas
    )


}
