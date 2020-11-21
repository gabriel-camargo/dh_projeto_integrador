package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.squareup.picasso.Picasso

class CastAdapter (private val dataset: List<CastModel>): RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.imgCast)
        private val ActorName: TextView = view.findViewById(R.id.txtCastActor)
        private val CharacterName: TextView = view.findViewById(R.id.txtCastCharacter)

        fun bind(cast: CastModel) {
            ActorName.text = cast.ActorName
            CharacterName.text = cast.CharacterName

            Picasso.get()
                .load(cast.image)
                .into(image)
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