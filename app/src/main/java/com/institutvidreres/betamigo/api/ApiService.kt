package com.institutvidreres.betamigo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("continents/")
    fun obtenerContinentes(
        @Query("user") user: String,
        @Query("token") token: String,
        @Query("t") t: String
    ): Call<List<Continente>>

}
