package com.gabrielcamargo.projetointegrador.home.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.home.model.FilmeModel

class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val ivImage: ImageView = view.findViewById(R.id.ivImageCard)
    private val tvName: TextView = view.findViewById(R.id.tvNameCard)



    fun bind(filmeModel: FilmeModel) {
        tvName.text = filmeModel.nome

        val avatarDrawable = ContextCompat.getDrawable(view.context, filmeModel.imagem);

        ivImage.setImageDrawable(avatarDrawable)

    }


}