package com.example.mp3_kmp.logic.viewmodel

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mp3_kmp.R
import com.example.mp3_kmp.data.model.FaixaModel
import com.example.mp3_kmp.data.repository.MdPlayer
import kotlinx.android.synthetic.main.fragment_runnning_player.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class PlayerVM : ViewModel() {


    // =====================================================================================================


    private var _mReprodList: MutableLiveData<ArrayList<FaixaModel>> = MdPlayer.getRL()
    private var _mPosition: MutableLiveData<Int> = MdPlayer.getPos()
    //private var _mP: MutableLiveData<MediaPlayer> = MdPlayer.getMP()

    fun getMP() = MdPlayer.getMP()
    fun getPosition() = _mPosition
    fun getReprodList() = _mReprodList

    fun setMP(newMp : MediaPlayer){
        MdPlayer.setMP(newMp)

    }
    fun setRL(newRL : ArrayList<FaixaModel>){
        if(newRL != _mReprodList.value!!) {
            _mReprodList.value = newRL
            MdPlayer.setRL(newRL)
        }
    }
    fun setPos(newPos: Int){
        if(newPos != _mPosition.value!!) {
            _mPosition.value = newPos
            MdPlayer.setPos(newPos)
        }
    }

    // =====================================================================================================

    var isPlaying = MutableLiveData(false)
    var isDefaultTrack = MutableLiveData(true)

    var _Track: LiveData<FaixaModel> = Transformations
        .switchMap(_mPosition){
            MutableLiveData<FaixaModel>(_mReprodList.value!![_mPosition.value!!])
        }

    var _tempoTotal = MutableLiveData("00:00")
    var _tempoDecor = MutableLiveData( "0:00")
    var _progressBar = MutableLiveData(0)

    fun setTempoTotal(){
        _tempoTotal.value = setTimeLabel(MdPlayer.getMP().value!!.duration)
    }
    fun setTempoDecorrido(progress: Int){
        _tempoDecor.value = setTimeLabel(progress)
    }

    // =====================================================================================================

    fun setPlusPosition(){
        if(_mPosition.value!! < _mReprodList.value!!.size){
            _mPosition.value = _mPosition.value!! + 1
        }
        else {
            println("----------------- > Limit_Reached ++")
        }

    }
    fun setSubPosition(){
        if(_mPosition.value!! > 0 && 0 < _mReprodList.value!!.size){
            _mPosition.value = _mPosition.value!! - 1
        }
        else {
            println("----------------- > Limit_Reached --")
        }

    }

    suspend fun updateTiming(){
        CoroutineScope(Default).launch {
            while(isPlaying.value!!) {
                delay(500)

                CoroutineScope(Main).launch {
                    _tempoDecor.value = setTimeLabel(MdPlayer.getMP().value!!.currentPosition)
                    _progressBar.value = MdPlayer.getMP().value!!.currentPosition
                }
            }
        }
    }
    private fun setTimeLabel(time: Int?): String {

        val minutos = time!! / 1000 / 60
        val segundos = time / 1000 % 60

        var label = "$minutos:"

        if (segundos < 10) label += "0"
        label += segundos

        return label
    }


    fun _Pause(){
        MdPlayer._Pause()
        isPlaying.value = false

    }
    fun _Play(){
        MdPlayer._Play()
        isPlaying.value = true

        CoroutineScope(Default).launch {
            updateTiming()
        }
    }














}