package com.example.mp3_kmp.view.fragment


import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mp3_kmp.R
import com.example.mp3_kmp.logic.viewmodel.PlayerVM
import kotlinx.android.synthetic.main.fragment_runnning_player.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch


class RunnningPlayerFragment : Fragment() {

    private val mmr = MediaMetadataRetriever()
    lateinit var playerVM: PlayerVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_runnning_player, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        playerVM = ViewModelProvider(activity!!).get(PlayerVM::class.java)

        playerVM._Track.observe(viewLifecycleOwner, Observer {
            killMP()
            createMP()

            setSeekBar()
            setAssets()

            if (playerVM.isDefaultTrack.value!!) {
                _Play()
                _Pause()
            }
            else _Play()

        })

        playerVM._progressBar.observe(viewLifecycleOwner, Observer { music_prog ->
            md_fragment_trackProgress.progress = music_prog
        })
        playerVM._tempoDecor.observe(viewLifecycleOwner, Observer { time_prog ->
            md_fragment_tempoDecorrido.text = time_prog
        })
        playerVM._tempoTotal.observe(viewLifecycleOwner, Observer { time_total ->
            md_fragment_tempoTotal.text = time_total
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        md_fragment_next.setOnClickListener { _Next() }
        md_fragment_previus.setOnClickListener { _Previus() }
        md_fragment_pauseplay.setOnClickListener { _PlayPause() }

    }

    private fun createMP(){

        val uri = Uri.parse(playerVM._Track.value!!.mDirect)
        val newMP = MediaPlayer.create(this.context, uri)

        playerVM.setMP(newMP)

    }
    private fun killMP(){
        playerVM.getMP().value!!.stop()
        playerVM.getMP().value!!.release()
    }

    private fun _Next(){
        if(playerVM.getPosition().value!! < playerVM.getReprodList().value!!.size){
            playerVM.isDefaultTrack.value = false
            playerVM.setPlusPosition()
        }
    }
    private fun _Previus(){
        if(playerVM.getPosition().value!! > 0){
            playerVM.isDefaultTrack.value = false
            playerVM.setSubPosition()
        }
    }

    private fun _Pause(){
        playerVM._Pause()
        md_fragment_pauseplay.background = resources.getDrawable(R.drawable.ic_play)

    }
    private fun _Play(){
            playerVM._Play()
            md_fragment_pauseplay.background = resources.getDrawable(R.drawable.ic_pause)
    }
    private fun _PlayPause(){
        if (playerVM.getMP().value!!.isPlaying) {

           _Pause()

        } else {

           _Play()
        }
    }


    private fun setAssets(){

        mmr.setDataSource(playerVM._Track.value!!.mDirect)
        Glide.with(context!!)
                .asBitmap()
                .load(mmr.embeddedPicture)
                .into(md_fragment_cover)
        md_fragment_Title.text = playerVM._Track.value!!.mNomefaixa

        playerVM.setTempoTotal()

    }


    private fun setSeekBar(){
        md_fragment_trackProgress.max = playerVM.getMP().value!!.duration
        md_fragment_trackProgress.progress = 0

        setSeekBarListener()
    }
    private fun setSeekBarListener(){

        md_fragment_trackProgress.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    md_fragment_trackProgress.progress = progress
                    playerVM.setTempoDecorrido(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                playerVM.getMP().value!!.seekTo(md_fragment_trackProgress.progress)
                playerVM.setTempoDecorrido(md_fragment_trackProgress.progress)

            }

        })

    }
































}
