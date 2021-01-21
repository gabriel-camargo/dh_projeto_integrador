package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieCrossRefEntity
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
@Dao
interface ListMovieCrossRefDao {
    @Insert
    suspend fun inserirListMovieCrossRef(listMovieCrossRef: ListMovieCrossRefEntity):Long
    @Query("SELECT * FROM ListMovieCrossRefEntity WHERE listMovieId = :id")
    suspend fun obterlistsMoviestMovieCrossRef(id: Long): MutableList<ListMovieCrossRefEntity>
    @Query("DELETE FROM ListMovieCrossRefEntity WHERE listMovieId = :listId and movieId = :movieId")
    suspend fun removeMovieFromList(listId: Long, movieId: Int)
}