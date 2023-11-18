package com.smartmeds.smartmeds.fragments.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartmeds.smartmeds.R
import com.smartmeds.smartmeds.fragments.Horarios

class horariosViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val horario = view.findViewById<TextView>(R.id.horarioSelected)
    val pastilla = view.findViewById<TextView>(R.id.pastilla)

    fun render(HorarioModel:Horarios){
        horario.text = HorarioModel.horario
        pastilla.text = HorarioModel.pastilla



    }
}