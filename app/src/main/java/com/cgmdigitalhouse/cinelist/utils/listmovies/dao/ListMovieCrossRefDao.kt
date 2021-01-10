package com.cgmdigitalhouse.cinelist.utils.listmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import com.cgmdigitalhouse.cinelist.utils.listmovies.entity.ListMovieEntity
@Dao
interface ListMovieCrossRefDao {
    @Insert
    suspend fun addListMovieCrossRef(listMovieCrossRef:ListMovieEntity)
}