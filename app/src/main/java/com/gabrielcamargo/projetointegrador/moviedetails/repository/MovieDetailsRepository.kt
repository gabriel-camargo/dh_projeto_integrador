package com.gabrielcamargo.projetointegrador.moviedetails.repository

class MovieDetailsRepository {
    private val client = IMovieDetailsEndpoint.endpoint

    suspend fun getMovieDetails(id: Int) = client.getMovieDetails(id)


//    val perdidoEmMarte = MovieDetailsModel(R.drawable.photo1, "Perdido em Marte", 8.0, "Ficção científica", 2015, "2h 31min")
//
//    fun getMovieDetails(callback: (movieDetails: MovieDetailsModel) -> Unit) {
//        callback.invoke(perdidoEmMarte)
//    }
}