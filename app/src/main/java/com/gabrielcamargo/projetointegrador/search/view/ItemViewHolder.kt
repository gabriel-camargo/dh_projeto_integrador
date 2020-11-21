package com.gabrielcamargo.projetointegrador.search.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.search.model.FilmeModel

class ItemViewHolder (private val view: View): RecyclerView.ViewHolder(view) {
    private val imagem = view.findViewById<ImageView>(R.id.ivFilmeSearch)
    private val nome = view.findViewById<TextView>(R.id.txtNameSearch)
    private val pontuacao = view.findViewById<TextView>(R.id.txtPunctuationSearch)
    private val generoFilme = view.findViewById<TextView>(R.id.txtKindMovieSearch)
    private val ano = view.findViewById<TextView>(R.id.txtYearSearch)

    fun bind(filmeModel: FilmeModel) {
        nome.text = filmeModel.nome
        pontuacao.text = filmeModel.nota.toString() +" / 10"
        generoFilme.text = filmeModel.generoFilme
        ano.text = filmeModel.ano.toString()

        val avatarDrawable = ContextCompat.getDrawable(view.context, filmeModel.imagem);

        imagem.setImageDrawable(avatarDrawable)

    }
}