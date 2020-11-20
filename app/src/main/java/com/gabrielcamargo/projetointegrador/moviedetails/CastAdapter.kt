package com.gabrielcamargo.projetointegrador.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.squareup.picasso.Picasso

class CastAdapter (private val dataset: List<Cast>): RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imagem: ImageView = view.findViewById(R.id.imgCast)
        private val nomeAtor: TextView = view.findViewById(R.id.txtCastActor)
        private val nomePersonagem: TextView = view.findViewById(R.id.txtCastCharacter)

        fun bind(elenco: Cast) {
            nomeAtor.text = elenco.nomeAtor
            nomePersonagem.text = elenco.nomePersonagem

            Picasso.get()
                .load(elenco.imagem)
                .into(imagem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)

        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}