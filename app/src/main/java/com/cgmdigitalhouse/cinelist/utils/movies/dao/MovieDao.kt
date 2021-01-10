package com.cgmdigitalhouse.cinelist.utils.movies.dao

import androidx.room.Dao
import androidx.room.Insert
import com.cgmdigitalhouse.cinelist.utils.movies.entity.MovieEntity
@Dao
interface MovieDao {
    @Insert
    suspend fun addMovie(movie:MovieEntity)
}