package com.gabrielcamargo.projetointegrador.moviedetails.cast.repository

class CastRepository{
    private val client = ICastEndpoint.endpoint

    suspend fun getCast(id: Int) = client.getCast(id)
}