package com.example.mp3_kmp.logic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mp3_kmp.data.model.ArtistaModel
import com.example.mp3_kmp.data.repository.MscSource

class DetArtistVM : ViewModel() {

    private val _mSelectedArtist : MutableLiveData<ArtistaModel> = MutableLiveData()

    fun setSelectedArtist(newArtistShow : ArtistaModel){ _mSelectedArtist.value = newArtistShow}
    fun getSelectedArtist(): LiveData<ArtistaModel> = _mSelectedArtist


}