package com.institutvidreres.betamigo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.institutvidreres.betamigo.adapter.PartidosAdapter
import com.institutvidreres.betamigo.api.ApiService
import com.institutvidreres.betamigo.api.Partido
import com.institutvidreres.betamigo.databinding.FragmentInicioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InicioFragment : Fragment() {

    private lateinit var apiService: ApiService
    private lateinit var binding: FragmentInicioBinding
    private lateinit var partidosAdapter: PartidosAdapter

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
        configurarRecyclerView()

        // Realizar la solicitud
        obtenerPartidos()
    }

    private fun configurarRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.soccersapi.com/v2.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear instancia de ApiService
        apiService = retrofit.create(ApiService::class.java)
    }

    private fun configurarRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        partidosAdapter = PartidosAdapter()
        binding.recyclerView.adapter = partidosAdapter
    }

    private fun obtenerPartidos() {
        val call = apiService.obtenerPartidos(
            user = "ruben.huertas",
            token = "d5eaede04e495b041af9581af3ea2316",
            t = "list"
        )

        call.enqueue(object : Callback<List<Partido>> {
            override fun onResponse(call: Call<List<Partido>>, response: Response<List<Partido>>) {
                if (response.isSuccessful) {
                    val partidos = response.body()
                    // Actualizar el adaptador con los datos recibidos
                    partidosAdapter.actualizarPartidos(partidos)
                } else {
                    // Manejar errores de la respuesta
                    mostrarError("Error en la respuesta de la API: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Partido>>, t: Throwable) {
                // Manejar errores de la solicitud
                mostrarError("Error al realizar la solicitud: ${t.message}")
            }
        })
    }

    private fun mostrarError(mensaje: String) {
        // Implementar la lógica para mostrar el mensaje de error en la interfaz de usuario
        // Por ejemplo, puedes utilizar un TextView para mostrar el mensaje de error.
        // textViewError.text = mensaje
    }
}
