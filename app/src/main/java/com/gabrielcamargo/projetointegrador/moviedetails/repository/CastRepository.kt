package com.gabrielcamargo.projetointegrador.moviedetails.repository

import android.content.Context
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.model.CastModel

class CastRepository{
    private val client = ICastEndpoint.endpoint

    suspend fun getCast(id: Int) = client.getCast(id)
}