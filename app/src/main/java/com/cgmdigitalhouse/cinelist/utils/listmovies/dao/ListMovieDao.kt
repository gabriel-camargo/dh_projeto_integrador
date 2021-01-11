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
    suspend fun obterlistsMovies(): MutableList<ListMovieEntity>


}