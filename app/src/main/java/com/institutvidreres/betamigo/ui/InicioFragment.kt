import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.institutvidreres.betamigo.adapter.ContinentesAdapter
import com.institutvidreres.betamigo.api.ApiService
import com.institutvidreres.betamigo.api.Continente
import com.institutvidreres.betamigo.databinding.FragmentInicioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InicioFragment : Fragment() {

    private lateinit var apiService: ApiService
    private lateinit var binding: FragmentInicioBinding
    private lateinit var continentesAdapter: ContinentesAdapter

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

        // Realizar la solicitud de los continentes
        obtenerContinentes()
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
        continentesAdapter = ContinentesAdapter() // Inicializa el adaptador de continentes
        binding.recyclerView.adapter = continentesAdapter // Asigna el adaptador al RecyclerView
    }

    private fun obtenerContinentes() {
        val call = apiService.obtenerContinentes(
            user = "ruben.huertas",
            token = "d5eaede04e495b041af9581af3ea2316",
            t = "list"
        )

        call.enqueue(object : Callback<List<Continente>> {
            override fun onResponse(call: Call<List<Continente>>, response: Response<List<Continente>>) {
                if (response.isSuccessful) {
                    val continentes = response.body()
                    continentes?.let {
                        // Actualizar el adaptador con los datos recibidos
                        continentesAdapter.actualizarContinentes(it)
                    }
                } else {
                    // Manejar errores de la respuesta
                    mostrarError("Error en la respuesta de la API: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Continente>>, t: Throwable) {
                // Manejar errores de la solicitud
                Log.e("InicioFragment", "Error al realizar la solicitud: ${t.message}", t)
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
