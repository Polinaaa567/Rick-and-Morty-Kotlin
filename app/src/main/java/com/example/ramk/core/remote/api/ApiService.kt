package com.example.ramk.core.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

import com.example.ramk.core.model.Character


interface ApiService {
    @GET(value = "character")
    suspend fun getCharacters(
        @Query(value = "page") page: Int = 1
    ): Response<Character>
}
