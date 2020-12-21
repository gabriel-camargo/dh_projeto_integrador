package com.cgmdigitalhouse.cinelist.moviedetails.cast.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cgmdigitalhouse.cinelist.R
import com.cgmdigitalhouse.cinelist.moviedetails.cast.model.CastModel
import com.squareup.picasso.Picasso

class CastAdapter (private val dataset: List<CastModel>): RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageVw: ImageView = view.findViewById(R.id.imgCast)
        private val ActorName: TextView = view.findViewById(R.id.txtCastActor)
        private val CharacterName: TextView = view.findViewById(R.id.txtCastCharacter)

        fun bind(cast: CastModel) {
            ActorName.text = cast.name
            CharacterName.text = cast.character

            Picasso.get().load(cast.getPathImage()).into(imageVw);

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