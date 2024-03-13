package com.institutvidreres.betamigo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("my/leagues")
    fun obtenerLigas(
        @Query("api_token") apiToken: String,
        @Query("include") include: String
    ): Call<Liga>
}

