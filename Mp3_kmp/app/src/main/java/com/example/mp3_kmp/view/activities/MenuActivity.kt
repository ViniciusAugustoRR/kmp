package com.example.mp3_kmp.view.activities

import android.media.MediaMetadataRetriever
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mp3_kmp.R
import com.example.mp3_kmp.logic.FragmentsInstances
import com.example.mp3_kmp.logic.viewmodel.AlbumVM
import com.example.mp3_kmp.logic.viewmodel.ArtistVM
import com.example.mp3_kmp.logic.viewmodel.PlayerVM
import com.example.mp3_kmp.view.fragment.RunnningPlayerFragment
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*

class MenuActivity : FragmentActivity() {
    private val mmr = MediaMetadataRetriever()
    private lateinit var playerVM: PlayerVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerVM = ViewModelProvider(this).get(PlayerVM::class.java)
        playerVM._Track.observe(this, Observer { faixa ->
            menu_main_runningTitle.text = faixa.mNomefaixa
            menu_main_runningAlbum.text = faixa.mNomeAlbum

            mmr.setDataSource(faixa.mDirect)
            Glide.with(this).asBitmap()
                    .load(mmr.embeddedPicture)
                    .into(menu_main_runningCover)

        })

        setPanelSlideLayout()
        setAssets()

    }

    private fun setPanelSlideLayout(){
        val mSliderListener = object :SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View, slideOffset: Float) {
                menu_main_dragger.alpha = 1 - slideOffset
                menu_main_scrolled.alpha = slideOffset
                println("--------------------------------> $slideOffset")
            }
            override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?,
                                             newState: SlidingUpPanelLayout.PanelState?) {

                //TODO
            }

        }
        menu_main_sliderlayout.addPanelSlideListener(mSliderListener)

    }

    private fun setAssets(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.menu_main_runningFrame, RunnningPlayerFragment())
            .add(R.id.menu_main_frame, FragmentsInstances.mainFrags[0])
            .commit()

        //ToolBar
        menu_main_toolbar.title = getString(R.string.musicas_title)
        menu_main_toolbar.setTitleTextColor(getColor(R.color.white))

    }

}
