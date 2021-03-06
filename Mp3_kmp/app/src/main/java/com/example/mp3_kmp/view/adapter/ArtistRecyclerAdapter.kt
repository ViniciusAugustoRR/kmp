package com.example.mp3_kmp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.mp3_kmp.R
import com.example.mp3_kmp.data.model.ArtistaModel
import kotlinx.android.synthetic.main.faixa_list_item.view.*

class ArtistRecyclerAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArtistaModel>() {

        override fun areItemsTheSame(oldItem: ArtistaModel, newItem: ArtistaModel): Boolean {
            TODO("not implemented")
        }

        override fun areContentsTheSame(oldItem: ArtistaModel, newItem: ArtistaModel): Boolean {
            TODO("not implemented")
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ArtistVH(
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
            is ArtistVH -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ArtistaModel>) {
        differ.submitList(list)
    }

    class ArtistVH
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ArtistaModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.card_faixa_nomeFaixa.text = item.ArtistaName
            itemView.card_faixa_nomeAlbum.text = item.Albums.size.toString()

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ArtistaModel)
    }
}
