package com.example.mp3_kmp.view.adapter

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.mp3_kmp.R
import com.example.mp3_kmp.data.model.FaixaModel
import kotlinx.android.synthetic.main.faixa_list_item.view.*
import kotlinx.android.synthetic.main.fragment_runnning_player.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch


class FaixaRecyclerAdapter(private val interaction: Interaction? = null) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FaixaModel>() {

        override fun areItemsTheSame(oldItem: FaixaModel, newItem: FaixaModel): Boolean {
            return oldItem.mDirect == newItem.mDirect
        }

        override fun areContentsTheSame(oldItem: FaixaModel, newItem: FaixaModel): Boolean {
            return oldItem.equals(newItem)
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return FaixaRecyclerVH(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.faixa_list_item,
                        parent,
                        false
                ),
                interaction)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FaixaRecyclerVH -> {
                holder.bind(differ.currentList[position], position)
            }
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }


    fun submitList(list: List<FaixaModel>) {
        differ.submitList(list)
    }


    class FaixaRecyclerVH
    constructor(
            itemView: View,
            private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: FaixaModel, position: Int) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            card_faixa_nomeFaixa.text = item.mNomefaixa
            card_faixa_nomeAlbum.text = item.mNomeAlbum


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: FaixaModel)
    }

    
}
