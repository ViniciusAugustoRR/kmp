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
import com.example.mp3_kmp.data.model.FaixaModel
import kotlinx.android.synthetic.main.faixa_list_item.view.*

class ArtistTracksRecyclerAdapter(private val interaction: Interaction? = null) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FaixaModel>() {

        override fun areItemsTheSame(oldItem: FaixaModel, newItem: FaixaModel): Boolean {
            return oldItem.mDirect == newItem.mDirect
        }

        override fun areContentsTheSame(oldItem: FaixaModel, newItem: FaixaModel): Boolean {
            return oldItem.mDirect == newItem.mDirect
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return FaixaVH(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.faixa_list_item,
                        parent,
                        false
                ),
                interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FaixaVH -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<FaixaModel>) {
        differ.submitList(list)
    }

    class FaixaVH
    constructor(
            itemView: View,
            private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        val mmr = MediaMetadataRetriever()

        fun bind(item: FaixaModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            mmr.setDataSource(item.mDirect)
            Glide.with(context)
                    .asBitmap()
                    .load(mmr.embeddedPicture)
                    .into(itemView.card_faixa_album)

            itemView.card_faixa_nomeFaixa.text = item.mNomefaixa
            itemView.card_faixa_nomeAlbum.text = item.mNomeAlbum

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: FaixaModel)
    }
}
