package com.cgmdigitalhouse.cinelist.movielistdetails.repository

import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieCrossRefDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

class MovieListDetailsRepository(
    private val listMovieDao: ListMovieDao,
    private val listMovieCrossRefDao: ListMovieCrossRefDao
) {
    suspend fun getListMoviesCrossRefEntity(id:Long): MutableList<ListMovieCrossRefEntity> = listMovieCrossRefDao.obterlistsMoviestMovieCrossRef(id)
    suspend fun findList(id: Long) = listMovieDao.findList(id)
    suspend fun editList(listMovieEntity: ListMovieEntity) = listMovieDao.editList(listMovieEntity)
    suspend fun deleteListById(id: Long) = listMovieDao.deleteListById(id)
}
