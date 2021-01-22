package com.cgmdigitalhouse.cinelist.favoritemovies.movielist.repository

import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

class MovieListRepository(private val movieListDao: ListMovieDao) {
    suspend fun inserirListMovie(listMovieEntity: ListMovieEntity) = movieListDao.inserirListMovie(listMovieEntity)
    suspend fun getListMovies(): MutableList<MovieListModel> = movieListDao.obterlistsMovies()
    suspend fun getAllMovieLists(): MutableList<ListMovieEntity> = movieListDao.getAllMovieLists()
    suspend fun searchWatchList() = movieListDao.searchWatchList()
//    suspend fun getListMovies() = movieListDao.obterListsMovies()
//    suspend fun getMovies() = movieListDao.obterMovies()
}
