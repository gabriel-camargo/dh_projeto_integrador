package com.gabrielcamargo.projetointegrador.moviedetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel

class PhotosRepository(private val context: Context) {
    val photo1 = PhotoModel(R.drawable.photo1)
    val photos = mutableListOf(photo1, photo1, photo1, photo1, photo1, photo1, photo1, photo1)

    fun getPhotos(callback: (photos: MutableList<PhotoModel>) -> Unit) {
        callback.invoke(photos)
    }
}