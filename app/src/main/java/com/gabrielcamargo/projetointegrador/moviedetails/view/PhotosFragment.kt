package com.gabrielcamargo.projetointegrador.Photos.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.repository.PhotosRepository
import com.gabrielcamargo.projetointegrador.moviedetails.view.PhotosAdapter
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.PhotosViewModel

class PhotosFragment : Fragment() {

    lateinit var _view: View
    private lateinit var _viewModel: PhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_photos, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel = ViewModelProvider(
                this,
                PhotosViewModel.PhotosViewModelFactory(PhotosRepository(_view.context))
        ).get(PhotosViewModel::class.java)

        _viewModel.photos.observe(viewLifecycleOwner, Observer {
            createPhotoList(it)
        })

        _viewModel.getPhotos()
    }

    fun createPhotoList(photos: MutableList<PhotoModel>) {
        val viewManagerPhotos = GridLayoutManager(activity, 2)
        val recyclerViewPhotos = view?.findViewById<RecyclerView>(R.id.rcyVwPhotos)

        val viewAdapterPhotos = PhotosAdapter(photos)

        recyclerViewPhotos?.apply {
            layoutManager = viewManagerPhotos
            adapter = viewAdapterPhotos
        }
    }

}