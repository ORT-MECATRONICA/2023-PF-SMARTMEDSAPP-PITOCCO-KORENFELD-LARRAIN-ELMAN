package com.smartmeds.smartmeds.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.smartmeds.smartmeds.R
import java.text.SimpleDateFormat

import java.util.*


class HomeFragment : Fragment() {
    lateinit var horaTextView: TextView
    lateinit var nombrePastillaTextView: TextView
    lateinit var horarioPastillaTextView: TextView

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval: Long = 1000 // Intervalo de actualización en milisegundos

    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            actualizarHora()
            handler.postDelayed(this, updateInterval)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        horaTextView = view.findViewById(R.id.hora)
        nombrePastillaTextView = view.findViewById(R.id.textoSiguientePastilla)
        horarioPastillaTextView = view.findViewById(R.id.textoSiguienteHorario)


        val database = FirebaseDatabase.getInstance()
        val referencePastilla = database.getReference("usuarios/valenpitocco/pastillas-dia/jueves/pastilla-1/nombre") // Reemplaza con la ruta de tus datos
        val referenceHorario = database.getReference("usuarios/valenpitocco/pastillas-dia/jueves/pastilla-1/horario") // Reemplaza con la ruta de tus datos

// Agrega un oyente para obtener los datos
        referencePastilla.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Aquí obtendrás los datos
                if (dataSnapshot.exists()) {
                    val valor = dataSnapshot.getValue(String::class.java)
                    if(valor!=null){
                        nombrePastillaTextView.text = "Siguiente pastilla: ${valor}"
                    }

                    // Haz algo con el valor, por ejemplo, muestra en un TextView

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Maneja errores aquí
            }
        })

        referenceHorario.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Aquí obtendrás los datos
                if (dataSnapshot.exists()) {
                    val valor = dataSnapshot.getValue(String::class.java)
                    if(valor!=null){
                        horarioPastillaTextView.text = "Siguiente horario: ${valor}"
                    }

                    // Haz algo con el valor, por ejemplo, muestra en un TextView

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Maneja errores aquí
            }
        })


        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // Obtener el nombre del día de la semana
        val nombreDia = obtenerNombreDiaSemana(dayOfWeek)
        if(nombreDia == "domingo") {

        }
        if(nombreDia == "lunes") {
        }
        if(nombreDia == "martes") {
        }
        if(nombreDia == "miercoles") {
        }
        if(nombreDia == "jueves") {
        }
        if(nombreDia == "viernes") {
        }
        if(nombreDia == "sabado") {
        }



        return view

    }

    override fun onResume() {
        super.onResume()
        handler.post(updateTimeRunnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(updateTimeRunnable)
    }

    private fun actualizarHora() {
        val formatoHora = SimpleDateFormat("HH:mm")
        val horaActual = Date()
        val horaFormateada = formatoHora.format(horaActual)

        horaTextView.text = horaFormateada
    }
    private fun obtenerNombreDiaSemana(dayOfWeek: Int): String {
        return when (dayOfWeek) {
            Calendar.SUNDAY -> "domingo"
            Calendar.MONDAY -> "lunes"
            Calendar.TUESDAY -> "martes"
            Calendar.WEDNESDAY -> "miercoles"
            Calendar.THURSDAY -> "jueves"
            Calendar.FRIDAY -> "viernes"
            Calendar.SATURDAY -> "sabado"
            else -> "día desconocido"
        }
    }
}



