package com.gabrielcamargo.projetointegrador.moviedetails.model

data class MovieDetailsModel(
        val image: Int
        , val name: String
        , val star: Double
        , val genre: String
        , val year: Int
        , val time: String
        , val summary: String
        , val photos: List<PhotoModel>
        , val reviews: List<ReviewModel>
        , val cast: List<CastModel>
)