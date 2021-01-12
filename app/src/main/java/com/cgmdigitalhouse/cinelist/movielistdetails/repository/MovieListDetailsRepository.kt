package com.cgmdigitalhouse.cinelist.movielistdetails.repository

import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieCrossRefDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity



class MovieListDetailsRepository(private val listMovieCrossRefDao: ListMovieCrossRefDao) {
    suspend fun getListMoviesCrossRefEntity(id:Long): MutableList<ListMovieCrossRefEntity> = listMovieCrossRefDao.obterlistsMoviestMovieCrossRef(id)
}
