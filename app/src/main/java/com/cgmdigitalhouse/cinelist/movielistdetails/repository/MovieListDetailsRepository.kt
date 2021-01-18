package com.cgmdigitalhouse.cinelist.movielistdetails.repository

import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieCrossRefDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity

class MovieListDetailsRepository(
    private val listMovieDao: ListMovieDao,
    private val listMovieCrossRefDao: ListMovieCrossRefDao
) {
    suspend fun getListMoviesCrossRefEntity(id:Long): MutableList<ListMovieCrossRefEntity> = listMovieCrossRefDao.obterlistsMoviestMovieCrossRef(id)
    suspend fun findList(id: Long) = listMovieDao.findList(id)
    suspend fun editList(id: Long, name: String, description: String) = listMovieDao.editList(id, name, description)
}
