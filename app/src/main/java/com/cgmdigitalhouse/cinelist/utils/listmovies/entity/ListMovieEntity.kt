package com.cgmdigitalhouse.cinelist.utils.listmovies.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "ListMovie")
data class ListMovieEntity (
    @PrimaryKey(autoGenerate = true)
    val listMovieId: Long,
    @ColumnInfo val name: String
)