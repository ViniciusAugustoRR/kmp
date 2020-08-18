package com.example.mp3_kmp.view.fragment.mainFrags


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.example.mp3_kmp.R
import com.example.mp3_kmp.data.model.AlbumModel
import com.example.mp3_kmp.logic.viewmodel.AlbumVM
import com.example.mp3_kmp.view.adapter.AlbumRecyclerAdapter
import com.example.mp3_kmp.view.adapter.ArtistAdapters.ArtistAlbumRecyclerAdapter
import com.example.mp3_kmp.view.adapter.FaixaRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_album_details.*
import java.lang.NullPointerException

class AlbumDetailsFragment : Fragment() {
    val ALBUM_BUNDLE_ID : String = "SELECTED_ALBUM"
    private lateinit var faixasRecyclerAdapter: FaixaRecyclerAdapter
    private lateinit var _mAlbum: AlbumModel

    companion object{

        fun newDetailedFragment(selectedAlbum: AlbumModel): AlbumDetailsFragment{
            val args = Bundle()
            args.putParcelable("SELECTED_ALBUM", selectedAlbum)

            val newInstance = AlbumDetailsFragment()
            newInstance.arguments = args

            return newInstance
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(arguments != null){
            _mAlbum = arguments!!.getParcelable(ALBUM_BUNDLE_ID)!!
        }

        faixasRecyclerAdapter = FaixaRecyclerAdapter()


        return inflater.inflate(R.layout.fragment_album_details, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initView()
        } catch (e: NullPointerException){
            Log.d("Fragment AlbumDetails", "Variavel Vazia")
        }
    }

    private fun initView(){
        Glide.with(context!!).asBitmap()
            .load(_mAlbum.faixas[0].mDirect)
            .into(album_detail_fragment_albumCover)

        album_detail_fragment_albumTitle.text = _mAlbum.albumNome

        faixasRecyclerAdapter.submitList(_mAlbum.faixas)
        album_detail_fragment_recycler.apply{
            layoutManager = LinearLayoutManager(this.context!!)
            adapter = faixasRecyclerAdapter
            setHasFixedSize(true)

        }
    }

}

