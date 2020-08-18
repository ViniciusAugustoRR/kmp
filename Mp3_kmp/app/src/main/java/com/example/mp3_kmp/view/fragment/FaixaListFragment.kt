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
import com.example.mp3_kmp.data.model.FaixaModel
import com.example.mp3_kmp.data.repository.MdPlayer
import com.example.mp3_kmp.logic.viewmodel.FaixaVM
import com.example.mp3_kmp.logic.viewmodel.PlayerVM
import com.example.mp3_kmp.view.adapter.FaixaRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_faixa.*


class FaixaListFragment(private val frag_Interation : OnTrackInteraction? = null) : Fragment(), FaixaRecyclerAdapter.Interaction {

    private lateinit var faixaAdapter: FaixaRecyclerAdapter
    private lateinit var faixaVM: FaixaVM
    private lateinit var playerVM: PlayerVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        faixaAdapter = FaixaRecyclerAdapter(this)
        faixaVM = ViewModelProvider(this).get(FaixaVM::class.java)


        faixaVM.getTracks().observe(this, Observer {
            faixaAdapter.notifyDataSetChanged()

        })

        return inflater.inflate(R.layout.fragment_faixa, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        playerVM = ViewModelProvider(activity!!).get(PlayerVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }


    // PREPARE RECYCLER
    private fun initView(){

        faixaAdapter.submitList(faixaVM.getTracks().value!!)

        faixa_fragment_recycler.apply {
            layoutManager = LinearLayoutManager(context!!)
            adapter = faixaAdapter
            setHasFixedSize(true)
        }

    }





    //ONCLICK_LISTENER METHODS
    interface OnTrackInteraction{
        fun onTrackClicked(track: FaixaModel, position: Int)
    }

    override fun onItemSelected(position: Int, item: FaixaModel) {
        frag_Interation?.onTrackClicked(item, position)

        playerVM.setRL(faixaVM.getTracks().value!!)
        playerVM.setPos(position)

    }


















}
