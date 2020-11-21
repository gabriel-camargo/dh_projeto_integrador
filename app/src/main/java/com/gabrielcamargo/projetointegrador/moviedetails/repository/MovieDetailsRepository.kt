package com.gabrielcamargo.projetointegrador.moviedetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel

class MovieDetailsRepository(private val context: Context) {

    val summary = "O astronauta Mark Watney (Matt Damon) é enviado a uma missão em Marte. Após uma severa tempestade ele é dado como morto, abandonado pelos colegas e acorda sozinho no misterioso planeta com escassos suprimentos, sem saber como reencontrar os companheiros ou retornar à Terra."

    val photo1 = PhotoModel(R.drawable.photo1)
    val photos = mutableListOf(photo1, photo1, photo1, photo1, photo1, photo1, photo1, photo1)

    val review1 = ReviewModel("Filme muito bom", 8.0)
    val reviews = mutableListOf(review1, review1, review1, review1)

    val mattDamon = CastModel("Matt Damon", "Mark Watney", R.drawable.img_cast)
    val cast = mutableListOf(mattDamon, mattDamon, mattDamon, mattDamon, mattDamon)

    fun getCast(callback: (cast: MutableList<CastModel>) -> Unit) {
        callback.invoke(cast)
    }

    fun getReviews(callback: (reviews: MutableList<ReviewModel>) -> Unit) {
        callback.invoke(reviews)
    }

    fun getPhotos(callback: (photos: MutableList<PhotoModel>) -> Unit) {
        callback.invoke(photos)
    }

    fun getSummary(callback: (cast: String) -> Unit) {
        callback.invoke(summary)
    }

}
