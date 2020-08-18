package com.example.mp3_kmp.view.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mp3_kmp.R
import com.example.mp3_kmp.data.model.ArtistaModel
import com.example.mp3_kmp.logic.FragmentsInstances
import com.example.mp3_kmp.logic.viewmodel.AlbumVM
import com.example.mp3_kmp.logic.viewmodel.ArtistVM
import com.example.mp3_kmp.view.adapter.ArtistRecyclerAdapter
import com.example.mp3_kmp.view.fragment.mainFrags.ArtistDetailsFragment
import kotlinx.android.synthetic.main.fragment_artist.*





class ArtistListFragment(private val frag_interaction : OnArtistInteraction? = null) : Fragment(),
    ArtistRecyclerAdapter.Interaction {

    lateinit var artistRecyclerAdapter : ArtistRecyclerAdapter
    lateinit var artistVM : ArtistVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        artistVM = ViewModelProvider(activity!!).get(ArtistVM::class.java)
        artistRecyclerAdapter = ArtistRecyclerAdapter(this)

        return inflater.inflate(R.layout.fragment_artist, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }
    private fun initView(){
        artistRecyclerAdapter.submitList(artistVM.getArtists().value!!)
        artista_fragment_recycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = artistRecyclerAdapter
            setHasFixedSize(true)

        }

    }

    interface  OnArtistInteraction{
        fun onArtistClicked(artista: ArtistaModel,  position: Int)
    }
    override fun onItemSelected(position: Int, item: ArtistaModel) {
        frag_interaction?.onArtistClicked(item, position)

        fragmentManager!!.beginTransaction()
                .replace(R.id.menu_main_frame, ArtistDetailsFragment.newArtistInstance(item))
                .commit()

    }

}
