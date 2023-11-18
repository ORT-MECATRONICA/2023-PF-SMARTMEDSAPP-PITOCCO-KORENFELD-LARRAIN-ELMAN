package com.smartmeds.smartmeds.fragments
// Importa las bibliotecas necesarias
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener



class HorariosProvider {



    companion object{
        val nombrePastilla = "Pastilla 1"
        val horario = "0:00"
        val lunes = false
        val martes = false
        val miercoles = false
        val jueves = false
        val viernes = false
        val sabado = false
        val domingo = false

        val alarmasList = listOf<Horarios>(
            Horarios(nombrePastilla,
                horario,
                lunes,
                martes,
                miercoles,
                jueves,
                viernes,
                sabado,
                domingo,

            )
        )





    }

}