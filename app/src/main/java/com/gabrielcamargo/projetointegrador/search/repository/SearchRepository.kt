package com.gabrielcamargo.projetointegrador.search.repository

import android.content.Context

class SearchRepository {
    private val client = ISearchEndpoint.endpoint

    suspend fun searchMovies(query: String, page: Int = 1) = client.searchMovies(query, page)
}