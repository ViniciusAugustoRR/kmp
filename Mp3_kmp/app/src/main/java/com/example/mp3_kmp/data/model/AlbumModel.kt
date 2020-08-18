package com.example.mp3_kmp.data.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
class AlbumModel(
    var albumNome: String?,
    var faixas: ArrayList<FaixaModel>
): Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("faixas")
    ) {}

    fun getTracks() = faixas.sortedWith(compareBy {it.mNomefaixa})
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(albumNome)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumModel> {
        override fun createFromParcel(parcel: Parcel): AlbumModel {
            return AlbumModel(parcel)
        }

        override fun newArray(size: Int): Array<AlbumModel?> {
            return arrayOfNulls(size)
        }
    }


}

