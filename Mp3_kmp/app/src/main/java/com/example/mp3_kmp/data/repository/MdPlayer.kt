package com.example.mp3_kmp.data.repository

import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import com.example.mp3_kmp.data.model.FaixaModel
import java.text.FieldPosition

object MdPlayer {

    private var MdaPlayer = MediaPlayer()
    private var reproductionList = MscSource.mTracks
    private var position: Int = 0

    fun getMP() = MutableLiveData(MdaPlayer)
    fun getRL() = MutableLiveData(reproductionList)
    fun getPos() = MutableLiveData(position)

    fun setMP(newMp: MediaPlayer){
        MdaPlayer = newMp

    }
    fun setRL(newReprod: ArrayList<FaixaModel>){
        reproductionList = newReprod
    }
    fun setPos(newPos: Int){
        position = newPos
    }

    fun _Pause(){
        MdaPlayer.pause()

    }
    fun _Play(){
        MdaPlayer.start()

    }

}