package com.gabrielcamargo.projetointegrador.moviedetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.favoritemovies.watchlist.model.MovieModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.MovieDetailsModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.PhotoModel
import com.gabrielcamargo.projetointegrador.moviedetails.model.ReviewModel

class MovieDetailsRepository(private val context: Context) {

    val summary = "O astronauta Mark Watney (Matt Damon) é enviado a uma missão em Marte. Após uma severa tempestade ele é dado como morto, abandonado pelos colegas e acorda sozinho no misterioso planeta com escassos suprimentos, sem saber como reencontrar os companheiros ou retornar à Terra."
    val photo1 = PhotoModel(R.drawable.photo1)
    val photos = listOf(photo1, photo1, photo1, photo1, photo1, photo1, photo1, photo1)
    val review1 = ReviewModel("Filme muito bom")
    val reviews = listOf(review1, review1, review1, review1)
    val mattDamon = CastModel("Matt Damon", "Mark Watney", R.drawable.img_cast)
    val cast = mutableListOf(mattDamon, mattDamon, mattDamon, mattDamon, mattDamon)

    fun getMovieDetails(callback: (movieDetails: MovieDetailsModel) -> Unit) {
        callback.invoke(
                MovieDetailsModel(R.drawable.img_movie
                                ,"Perdido em Marte"
                                , 8.0
                                , "Ficção científica"
                                , 2015
                                , "2h 31min"
                                , summary
                                , photos
                                , reviews
                                , cast)
        )
    }

    fun getCast(callback: (cast: MutableList<CastModel>) -> Unit) {
        callback.invoke(cast)
    }

}
