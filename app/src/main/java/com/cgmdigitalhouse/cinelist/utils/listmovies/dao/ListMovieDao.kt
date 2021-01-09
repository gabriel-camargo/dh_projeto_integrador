package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.Insert
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

interface ListMovieDao {
    @Insert
    suspend fun addListMovie(listMovie:ListMovieEntity)

}