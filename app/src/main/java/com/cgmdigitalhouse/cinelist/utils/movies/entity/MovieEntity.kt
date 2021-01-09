package com.cgmdigitalhouse.cinelist.utils.movies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieEntity (
    @PrimaryKey val movieId: Long,
)
