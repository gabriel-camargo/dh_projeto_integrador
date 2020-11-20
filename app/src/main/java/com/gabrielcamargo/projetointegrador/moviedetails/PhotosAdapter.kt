package com.gabrielcamargo.projetointegrador.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.squareup.picasso.Picasso

class PhotosAdapter (private val dataset: List<Photo>): RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imagem: ImageView = view.findViewById(R.id.imgPhoto)

        fun bind(foto: Photo) {
            Picasso.get()
                .load(foto.imagem)
                .into(imagem)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photos, parent, false)

        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}