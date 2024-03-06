package com.institutvidreres.betamigo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("leagues/")
    fun obtenerPartidos(
        @Query("ruben.huertas") user: String,
        @Query("d5eaede04e495b041af9581af3ea2316") token: String,
        @Query("https://api.soccersapi.com/v2.2/leagues/?user=ruben.huertas&token=d5eaede04e495b041af9581af3ea2316&t=list") t: String
    ): Call<List<Partido>>

}