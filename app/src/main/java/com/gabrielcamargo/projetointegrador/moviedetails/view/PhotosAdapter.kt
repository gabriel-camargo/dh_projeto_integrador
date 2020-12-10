package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.PosterModel
import com.squareup.picasso.Picasso

class PhotosAdapter (private val dataset: List<PosterModel>): RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageVw: ImageView = view.findViewById(R.id.imgPhoto)

        fun bind(foto: PosterModel) {

            Glide.with(itemView.context)
                .load(foto.getPathPoster())
                .transform()
                .transition(DrawableTransitionOptions.withCrossFade())
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