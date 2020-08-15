package com.example.mp3_kmp.logic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mp3_kmp.data.model.ArtistaModel
import com.example.mp3_kmp.data.repository.MscSource

class ArtistVM: ViewModel() {

    private val _mArtists : MutableLiveData<ArrayList<ArtistaModel>> = MscSource.getArists()

    fun getArtists(): LiveData<ArrayList<ArtistaModel>> = _mArtists


}