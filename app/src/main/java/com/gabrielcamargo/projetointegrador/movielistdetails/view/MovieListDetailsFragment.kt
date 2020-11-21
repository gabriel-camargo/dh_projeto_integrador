package com.gabrielcamargo.projetointegrador.movielistdetails.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.movielistdetails.viewmodel.MovieListDetailsViewModel

class MovieListDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListDetailsFragment()
    }

    private lateinit var viewModel: MovieListDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.movie_list_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieListDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}