package com.example.mp3_kmp.data.repository

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import com.example.mp3_kmp.data.model.AlbumModel
import com.example.mp3_kmp.data.model.ArtistaModel
import com.example.mp3_kmp.data.model.FaixaModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.collections.ArrayList

object MscSource {

    val mDirect: ArrayList<String> =
        readDirects(Environment.getExternalStorageDirectory())

    var mTracks : ArrayList<FaixaModel>
    var mAlbums : ArrayList<AlbumModel>
    var mArtists : ArrayList<ArtistaModel>

    init {
        mTracks = createMusics(mDirect)
        mAlbums = createAlbums()
        mArtists = createArtists()

    }



    fun createMusics(directs: ArrayList<String>): ArrayList<FaixaModel> {

            val newDirects = ArrayList<FaixaModel>()
            val mmr = MediaMetadataRetriever()
            var mmrData : ArrayList<String?>

        for (file in directs) {
                    mmr.setDataSource(file)

                    mmrData = arrayListOf(
                        mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE),
                        mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
                        mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST))

                    for(item in mmrData){
                        if(item.isNullOrBlank()){
                            mmrData[mmrData.indexOf(item)] = "Desconhecido"

                        }
                    }

                    if (mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION).toLong() < 900000) {
                        newDirects.add(
                            FaixaModel(
                                file,
                                mmrData[0],
                                mmrData[1],
                                mmrData[2]
                            )
                        )
                    }

                }

        return ArrayList(newDirects.sortedWith(compareBy { it.mNomefaixa}))
    }
    fun getTracks() = MutableLiveData(mTracks)

    fun createAlbums(): ArrayList<AlbumModel>{

        val albums = ArrayList<AlbumModel>()
        var faixas = ArrayList<FaixaModel>()
        var album_nm : String? = "_default"

        for(track in mTracks.sortedWith(compareBy { it.mNomeAlbum })){

            if(album_nm == "_default"){
                album_nm = track.mNomeAlbum
            }

            if(album_nm!! == track.mNomeAlbum){
                faixas.add(track)
            } else {

                albums.add(AlbumModel(
                    album_nm,
                    faixas
                ))
                album_nm = track.mNomeAlbum
                faixas = ArrayList()
                faixas.add(track)
            }

        }

        return albums
    }
    fun getAlbums() = MutableLiveData(mAlbums)

    fun createArtists(): ArrayList<ArtistaModel>{

        val artistas = ArrayList<ArtistaModel>()
        var albums = ArrayList<AlbumModel>()
        var artista_nm : String? = "_default"

        for(album in mAlbums.sortedWith(compareBy { it.faixas[0].mNomeArtista })){
            if(artista_nm == "_default"){
                artista_nm = album.faixas[0].mNomeArtista
            }

            if(artista_nm!! == album.faixas[0].mNomeArtista){
                albums.add(album)

            } else {
                artistas.add(ArtistaModel(
                    artista_nm,
                    albums
                ))

                artista_nm = album.faixas[0].mNomeArtista
                albums = ArrayList()
                albums.add(album)

            }

        }

        return artistas
    }

    fun getArists() = MutableLiveData(mArtists)



    fun readDirects(root: File): ArrayList<String> {
        val files: ArrayList<String> = ArrayList()

        for (file in root.listFiles()) {
            if (file.isDirectory) {
                files.addAll(readDirects(file))
            } else {

                if (file.name.endsWith(".mp3")) {
                    files.add(file.toString())

                }
            }
        }

        return files
    }

    /*
    fun getImage(pos: Int): ByteArray? {
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(mDirect[pos])



        return mmr.embeddedPicture
    }
    fun drawableToBytes(drawable: Drawable): ByteArray {
        val bitmap: Bitmap?

        if(drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            bitmap =
                    Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(bitmap!!)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        return stream.toByteArray()
    }
    */

}