package com.example.mp3_kmp.logic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mp3_kmp.data.model.AlbumModel
import com.example.mp3_kmp.data.repository.MscSource

class DetAlbumVM : ViewModel() {

    private var _mSelectedAlbum : MutableLiveData<AlbumModel> = MutableLiveData()


    fun setSelectedAlbum(newAlbumShow : AlbumModel){ _mSelectedAlbum.value = newAlbumShow }
    fun getSelectedAlbum(): LiveData<AlbumModel> = _mSelectedAlbum


}