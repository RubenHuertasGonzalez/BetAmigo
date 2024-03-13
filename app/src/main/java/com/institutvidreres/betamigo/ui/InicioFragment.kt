package com.institutvidreres.betamigo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.institutvidreres.betamigo.api.ApiService
import com.institutvidreres.betamigo.api.Liga
import com.institutvidreres.betamigo.databinding.FragmentInicioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InicioFragment : Fragment() {

    private lateinit var apiService: ApiService
    private lateinit var binding: FragmentInicioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRetrofit()

        // Realizar la solicitud de las ligas
        obtenerLigas()
    }

    private fun configurarRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.sportmonks.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear instancia de ApiService
        apiService = retrofit.create(ApiService::class.java)
    }

    private fun obtenerLigas() {
        val call = apiService.obtenerLigas(
            apiToken = "2ho6dV0ol4y42ekuPxqiux45dEtxwzusMj3xbOeudM8WyRyZU5oyCOnvNk4v",
            include = ""
        )

        call.enqueue(object : Callback<Liga> {
            override fun onResponse(call: Call<Liga>, response: Response<Liga>) {
                if (response.isSuccessful) {
                    val ligas = response.body()
                    ligas?.let {
                        // Manejar la respuesta de la API aquí
                    }
                } else {
                    // Manejar errores de la respuesta
                    Log.e("InicioFragment", "Error en la respuesta de la API: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Liga>, t: Throwable) {
                // Manejar errores de la solicitud
                Log.e("InicioFragment", "Error al realizar la solicitud: ${t.message}", t)
            }
        })
    }
}
