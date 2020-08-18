package com.example.mp3_kmp.view.adapter.ArtistAdapters

import android.media.MediaMetadataRetriever
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.mp3_kmp.R
import com.example.mp3_kmp.data.model.AlbumModel
import kotlinx.android.synthetic.main.album_float_item.view.*

class ArtistAlbumRecyclerAdapter(private val interaction: Interaction? = null) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlbumModel>() {

        override fun areItemsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem.albumNome == newItem.albumNome
        }

        override fun areContentsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem.faixas == newItem.faixas
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return AlbumVH(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.album_float_item,
                        parent,
                        false
                ),
                interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AlbumVH -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<AlbumModel>) {
        differ.submitList(list)
    }

    class AlbumVH
    constructor(
            itemView: View,
            private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        val mmr = MediaMetadataRetriever()

        fun bind(item: AlbumModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            mmr.setDataSource(item.faixas[0].mDirect)

            Glide.with(this.context)
                .asBitmap()
                .load(mmr.embeddedPicture)
                .into(itemView.album_float_cover)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: AlbumModel)
    }
}
