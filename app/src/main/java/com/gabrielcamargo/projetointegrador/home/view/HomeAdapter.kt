package com.gabrielcamargo.projetointegrador.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.home.model.FilmeModel

class HomeAdapter (private val filmesModel: List<FilmeModel>, private val listener: (FilmeModel) -> Unit) :
    RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount() = filmesModel.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(filmesModel[position])
        holder.itemView.setOnClickListener { listener(filmesModel[position]) }
    }
}