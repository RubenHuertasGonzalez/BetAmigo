package com.institutvidreres.betamigo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.institutvidreres.betamigo.R
import com.institutvidreres.betamigo.api.Continente
import java.util.ArrayList

class ContinentesAdapter : RecyclerView.Adapter<ContinentesAdapter.ContinenteViewHolder>() {

    private val listaContinentes: MutableList<Continente> = ArrayList()

    fun actualizarContinentes(nuevaListaContinentes: List<Continente>) {
        listaContinentes.clear()
        listaContinentes.addAll(nuevaListaContinentes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinenteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_continente, parent, false)
        return ContinenteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContinenteViewHolder, position: Int) {
        val continente = listaContinentes[position]
        holder.bind(continente)
    }

    override fun getItemCount(): Int {
        return listaContinentes.size
    }

    inner class ContinenteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombre_continente)

        fun bind(continente: Continente) {
            nombreTextView.text = continente.nombre
        }
    }
}
