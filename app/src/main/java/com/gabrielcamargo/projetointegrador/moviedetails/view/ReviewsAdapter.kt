package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel
import com.squareup.picasso.Picasso

class ReviewsAdapter(private val dataset: List<ReviewModel>): RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {

    class ReviewsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val reviewTxt: TextView = view.findViewById(R.id.txtReview)

        fun bind(review: ReviewModel) {
            reviewTxt.text = review.review
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)

        return ReviewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}