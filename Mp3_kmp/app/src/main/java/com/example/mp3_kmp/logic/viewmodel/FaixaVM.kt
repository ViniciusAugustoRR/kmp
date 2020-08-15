package com.example.mp3_kmp.logic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mp3_kmp.data.model.FaixaModel
import com.example.mp3_kmp.data.repository.MscSource

class FaixaVM : ViewModel() {

    private val _mTracks : MutableLiveData<ArrayList<FaixaModel>> = MscSource.getTracks()

    fun getTracks(): LiveData<ArrayList<FaixaModel>> = _mTracks


}