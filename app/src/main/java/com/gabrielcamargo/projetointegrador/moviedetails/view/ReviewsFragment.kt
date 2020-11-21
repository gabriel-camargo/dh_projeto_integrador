package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel

class ReviewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var review: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            review = it.getString("REVIEW")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reviews, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManagerReviews = LinearLayoutManager(activity)
        val recyclerViewReviews = view.findViewById<RecyclerView>(R.id.rcyVwReviews)

        val review1 = ReviewModel("Filme muito bom")
        val reviews = listOf(review1, review1, review1, review1)

        val viewAdapterReviews = ReviewsAdapter(reviews)

        recyclerViewReviews.apply {
            layoutManager = viewManagerReviews
            adapter = viewAdapterReviews
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(review: String) =
            ReviewsFragment().apply {
                arguments = Bundle().apply {
                    putString("REVIEW", review)
                }
            }
    }
}