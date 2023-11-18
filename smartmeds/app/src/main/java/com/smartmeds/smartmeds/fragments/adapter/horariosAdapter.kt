package com.smartmeds.smartmeds.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartmeds.smartmeds.R
import com.smartmeds.smartmeds.fragments.Horarios

class horariosAdapter(private var horariosList: List<Horarios>) : RecyclerView.Adapter<horariosViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): horariosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return horariosViewHolder(layoutInflater.inflate(R.layout.item_horarios, parent, false))

    }

    override fun onBindViewHolder(holder: horariosViewHolder, position: Int) {

        var item = horariosList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int =  horariosList.size



}

