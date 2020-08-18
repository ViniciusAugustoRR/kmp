package com.example.mp3_kmp.data.model

import android.os.Parcel
import android.os.Parcelable

class ArtistaModel(
    var ArtistaName: String?,
    var Albums: ArrayList<AlbumModel>

) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("Albums")
    )

    fun getTracks(): ArrayList<FaixaModel>{
        val tracks = ArrayList<FaixaModel>()
        for(album in Albums) tracks.addAll(album.faixas)

        return ArrayList(tracks.sortedWith(compareBy { it.mNomefaixa }))
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ArtistaName)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<ArtistaModel> {
        override fun createFromParcel(parcel: Parcel): ArtistaModel {
            return ArtistaModel(parcel)
        }

        override fun newArray(size: Int): Array<ArtistaModel?> {
            return arrayOfNulls(size)
        }
    }

}