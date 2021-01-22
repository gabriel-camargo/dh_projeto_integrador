package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.*
import com.cgmdigitalhouse.cinelist.favoritemovies.movielist.model.MovieListModel
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

@Dao
interface ListMovieDao {
    @Insert
    suspend fun inserirListMovie(listMovieEntity: ListMovieEntity): Long
  
    @Query("SELECT listMovieId, name, description, (SELECT COUNT(*) FROM ListMovieCrossRefEntity where ListMovieCrossRefEntity.listMovieId = ListMovie.listMovieId) as qtd FROM ListMovie where listMovieId <> 1")
    suspend fun obterlistsMovies(): MutableList<MovieListModel>

    @Query("SELECT * FROM ListMovie where listMovieId")
    suspend fun getAllMovieLists(): MutableList<ListMovieEntity>
  
    @Query("SELECT * FROM ListMovie WHERE listMovieId= :id")
    suspend fun findList(id: Long): List<ListMovieEntity>

    @Query("SELECT COUNT(*) FROM ListMovie where listMovieId = 1")
    suspend fun searchWatchList() :List<String>

    @Update
    suspend fun editList(listMovieEntity: ListMovieEntity)

    @Query("DELETE FROM ListMovie WHERE listMovieId= :id")
    suspend fun deleteListById(id: Long)
}
