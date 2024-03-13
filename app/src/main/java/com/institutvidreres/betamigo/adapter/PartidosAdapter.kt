package com.institutvidreres.betamigo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.institutvidreres.betamigo.R
import com.institutvidreres.betamigo.api.Partido

class PartidosAdapter : RecyclerView.Adapter<PartidosAdapter.PartidoViewHolder>() {

    private var partidos: List<Partido> = emptyList()

    fun actualizarPartidos(nuevaLista: List<Partido>?) {
        nuevaLista?.let {
            partidos = it
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_partido, parent, false)
        return PartidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartidoViewHolder, position: Int) {
        val partido = partidos[position]
        holder.bind(partido)
    }

    override fun getItemCount(): Int {
        return partidos.size
    }

    class PartidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val equipoLocalTextView: TextView = itemView.findViewById(R.id.equipoLocalTextView)
        private val equipoVisitanteTextView: TextView = itemView.findViewById(R.id.equipoVisitanteTextView)
        private val resultadoTextView: TextView = itemView.findViewById(R.id.resultadoTextView)

        fun bind(partido: Partido) {
            equipoLocalTextView.text = partido.localTeam.name
            equipoVisitanteTextView.text = partido.visitorTeam.name
            resultadoTextView.text = "Local: ${partido.scores.localteam_score} - Visitante: ${partido.scores.visitorteam_score}"
        }
    }
}
