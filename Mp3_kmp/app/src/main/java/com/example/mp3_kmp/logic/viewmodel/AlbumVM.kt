package com.example.mp3_kmp.logic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mp3_kmp.data.model.AlbumModel
import com.example.mp3_kmp.data.repository.MscSource

class AlbumVM : ViewModel() {
    private val _mAlbums : MutableLiveData<ArrayList<AlbumModel>> = MscSource.getAlbums()

    fun getAlbums(): LiveData<ArrayList<AlbumModel>> = _mAlbums


}