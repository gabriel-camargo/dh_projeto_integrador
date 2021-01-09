package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.Insert
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity

interface ListMovieCrossRefDao {
    @Insert
    suspend fun addListMovieCrossRef(listMovieCrossRef:ListMovieEntity)
}