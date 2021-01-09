package com.cgmdigitalhouse.cinelist.utils.movies.dao

import androidx.room.Insert
import com.cgmdigitalhouse.cinelist.utils.movies.entity.MovieEntity

interface MovieDao {
    @Insert
    suspend fun addMovie(movie:MovieEntity)
}