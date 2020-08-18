package com.example.mp3_kmp.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mp3_kmp.R
import com.example.mp3_kmp.data.model.AlbumModel
import com.example.mp3_kmp.logic.FragmentsInstances
import com.example.mp3_kmp.logic.viewmodel.AlbumVM
import com.example.mp3_kmp.view.adapter.AlbumRecyclerAdapter
import com.example.mp3_kmp.view.fragment.mainFrags.AlbumDetailsFragment
import kotlinx.android.synthetic.main.fragment_album.*
import java.text.FieldPosition


class AlbumListFragment(private val frag_interaction : OnAlbumInteraction? = null) : Fragment(), AlbumRecyclerAdapter.Interaction {
    lateinit var albumAdapter: AlbumRecyclerAdapter
    lateinit var albumVM: AlbumVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        albumAdapter = AlbumRecyclerAdapter(this)
        albumVM = ViewModelProvider(this).get(AlbumVM::class.java)

        albumVM.getAlbums().observe(this, Observer {
            albumAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }
    private fun initView(){
        albumAdapter.submitList(albumVM.getAlbums().value!!)

        album_fragment_recycler.apply {
            layoutManager = LinearLayoutManager(context!!)
            adapter = albumAdapter
            setHasFixedSize(true)

        }


    }

    interface  OnAlbumInteraction{
        fun onAlbumClicked(album: AlbumModel, position: Int)
    }
    override fun onItemSelected(position: Int, item: AlbumModel) {
        frag_interaction?.onAlbumClicked(item, position)

        fragmentManager!!.beginTransaction()
            .replace(R.id.menu_main_frame, AlbumDetailsFragment.newDetailedFragment(item))
            .commit()


    }

}
