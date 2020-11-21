package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.repository.WatchlistRepository
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.viewmodel.WatchlistViewModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.MovieDetailsRepository
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.MovieDetailsViewModel

class ReviewsFragment : Fragment() {
    lateinit var _view: View
    private lateinit var _viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_reviews, container, false)

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel = ViewModelProvider(
                this,
                MovieDetailsViewModel.MovieDetailsViewModelFactory(MovieDetailsRepository(_view.context))
        ).get(MovieDetailsViewModel::class.java)

        _viewModel.reviews.observe(viewLifecycleOwner, Observer {
            createReviewList(it)
        })

        _viewModel.getReviews()

    }

    fun createReviewList(reviews: MutableList<ReviewModel>) {
        val viewManagerReviews = LinearLayoutManager(activity)
        val recyclerViewReviews = view?.findViewById<RecyclerView>(R.id.rcyVwReviews)

        val viewAdapterReviews = ReviewsAdapter(reviews)

        recyclerViewReviews?.apply {
            layoutManager = viewManagerReviews
            adapter = viewAdapterReviews
        }
    }

}