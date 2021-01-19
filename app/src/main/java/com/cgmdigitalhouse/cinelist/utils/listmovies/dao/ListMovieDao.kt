package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
@Dao
interface ListMovieDao {
    @Insert
    suspend fun inserirListMovie(listMovieEntity:ListMovieEntity) : Long
    @Query("SELECT * FROM ListMovie")
    suspend fun obterlistsMovies(): MutableList<ListMovieEntity>
    @Query ("SELECT * FROM ListMovie WHERE listMovieId= :id")
    suspend fun findList(id: Long): List<ListMovieEntity>
    @Update
    suspend fun editList(listMovieEntity:ListMovieEntity)


}