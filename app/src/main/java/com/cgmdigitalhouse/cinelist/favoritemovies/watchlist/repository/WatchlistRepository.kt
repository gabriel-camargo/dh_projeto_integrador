package com.cgmdigitalhouse.cinelist.favoritemovies.watchlist.repository


import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieCrossRefDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.dao.ListMovieDao
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity


class WatchlistRepository(private val listMovieCrossRefDao: ListMovieCrossRefDao) {
    suspend fun getWatchList(idUser:String): MutableList<ListMovieCrossRefEntity> = listMovieCrossRefDao.obterWatcListMoviestMovieCrossRef(1,idUser)
    suspend fun removeMovieFromList(movieId: Int) = listMovieCrossRefDao.removeMovieFromList(1, movieId)
}
