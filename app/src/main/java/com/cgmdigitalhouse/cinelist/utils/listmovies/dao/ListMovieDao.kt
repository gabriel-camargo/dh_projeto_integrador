package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
@Dao
interface ListMovieDao {
    @Insert
    suspend fun inserirListMovie(listMovieEntity:ListMovieEntity) : Long
    @Query("SELECT * FROM ListMovie")
    suspend fun obterListsMovies(): MutableList<ListMovieEntity>

    @Query("SELECT * FROM ListMovie where listMovieId <> 1 ")
    suspend fun obterMovies(): MutableList<ListMovieEntity>



}