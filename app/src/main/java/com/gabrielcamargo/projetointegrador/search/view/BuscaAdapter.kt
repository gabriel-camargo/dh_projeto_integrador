package com.gabrielcamargo.projetointegrador.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.search.model.FilmeModel

class BuscaAdapter(private var filmes: MutableList<FilmeModel>,private val listener: (FilmeModel) -> Unit): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_movie, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = filmes[position]

        holder.bind(item)
        holder.itemView.setOnClickListener { listener(filmes[position]) }
    }

    override fun getItemCount() = filmes.size
}