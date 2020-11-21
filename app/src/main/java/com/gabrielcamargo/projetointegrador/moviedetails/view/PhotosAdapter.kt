package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.squareup.picasso.Picasso

class PhotosAdapter (private val dataset: List<PhotoModel>): RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageVw: ImageView = view.findViewById(R.id.imgPhoto)

        fun bind(foto: PhotoModel) {
            Picasso.get()
                .load(foto.image)
                .into(imageVw)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)

        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}