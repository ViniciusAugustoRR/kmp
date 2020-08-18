package com.example.mp3_kmp.view.fragment.mainFrags


import android.media.MediaMetadataRetriever
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.example.mp3_kmp.R
import com.example.mp3_kmp.data.model.ArtistaModel
import com.example.mp3_kmp.view.adapter.ArtistAdapters.ArtistAlbumRecyclerAdapter
import com.example.mp3_kmp.view.adapter.ArtistAdapters.ArtistTracksRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_artist_details.*


class ArtistDetailsFragment : Fragment() {
    private val ARTIST_BUNDLE_ID = "SELECTED_ARTIST"
    private lateinit var _mArtist: ArtistaModel

    private val mmr = MediaMetadataRetriever()
    private lateinit var artAlbumAdapter : ArtistAlbumRecyclerAdapter
    private lateinit var artTrackAdapter : ArtistTracksRecyclerAdapter

    companion object{
        fun newArtistInstance(artist: ArtistaModel): ArtistDetailsFragment {
            val args = bundleOf(Pair("SELECTED_ARTIST", artist))

            val newInstance = ArtistDetailsFragment()
            newInstance.arguments = args

            return newInstance
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(arguments != null) _mArtist = arguments!!.getParcelable(ARTIST_BUNDLE_ID)!!

        artAlbumAdapter = ArtistAlbumRecyclerAdapter()
        artTrackAdapter = ArtistTracksRecyclerAdapter()

        return inflater.inflate(R.layout.fragment_artist_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artAlbumAdapter.submitList(_mArtist.Albums)
        artTrackAdapter.submitList(_mArtist.getTracks())

        artist_detail_fragment_band_albums_recyler.apply {
            layoutManager = LinearLayoutManager(this.context!!, LinearLayoutManager.HORIZONTAL, false)
            adapter = artAlbumAdapter
            setHasFixedSize(true)

        }
        artist_detail_fragment_band_faixas_recyler.apply {
            layoutManager = LinearLayoutManager(this.context!!)
            adapter = artTrackAdapter
            setHasFixedSize(true)

        }

        initView()
    }


    fun initView(){
        mmr.setDataSource(_mArtist.Albums[0].faixas[0].mDirect)
        Glide.with(context!!)
                .asBitmap()
                .load(mmr.embeddedPicture)
                .into(artist_detail_fragment_band_image)



    }




}
