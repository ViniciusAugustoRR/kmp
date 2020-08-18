package com.example.mp3_kmp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mp3_kmp.R
import com.example.mp3_kmp.view.fragment.mainFrags.AlbumDetailsFragment
import com.example.mp3_kmp.view.fragment.mainFrags.ArtistDetailsFragment
import kotlinx.android.synthetic.main.activity_actv_test_layout.*

class Actv_test_layout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actv_test_layout)

        supportFragmentManager
            .beginTransaction()
            .add(main_test_frame.id, ArtistDetailsFragment())
            .commit()

    }


}
