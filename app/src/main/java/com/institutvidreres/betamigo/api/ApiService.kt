package com.institutvidreres.betamigo.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://api.soccersapi.com/v2.2/stats/?user={{ruben.huertas}}&token={{d5eaede04e495b041af9581af3ea2316}}&t=match&id={{MATCH_ID}}")
    fun obtenerPartidos(): Call<List<Partido>>
}