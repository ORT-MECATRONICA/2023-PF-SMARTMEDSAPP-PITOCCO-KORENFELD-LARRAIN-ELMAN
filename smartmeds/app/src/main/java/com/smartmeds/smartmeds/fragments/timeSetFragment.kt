package com.smartmeds.smartmeds.fragments

import android.graphics.Typeface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.firebase.database.FirebaseDatabase
import com.smartmeds.smartmeds.R
import org.w3c.dom.Text

class timeSetFragment : Fragment() {

    companion object {
        fun newInstance() = timeSetFragment()
    }

    lateinit var horaPicker : EditText
    lateinit var lunesSelected : TextView
    lateinit var martesSelected : TextView
    lateinit var miercolesSelected : TextView
    lateinit var juevesSelected : TextView
    lateinit var viernesSelected : TextView
    lateinit var sabadoSelected : TextView
    lateinit var domingoSelected : TextView
    lateinit var guardarSelected : Chip
    lateinit var nombrePastilla : EditText
    lateinit var tipoPastilla : EditText
    lateinit var atrasSelected : Chip

    var lunes = false
    var martes = false
    var miercoles = false
    var jueves = false
    var viernes = false
    var sabado = false
    var domingo = false


    private lateinit var viewModel: TimeSetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_time_set, container, false)
        horaPicker = view.findViewById(R.id.horaSeleccionada)
        lunesSelected = view.findViewById(R.id.lunesText)
        martesSelected = view.findViewById(R.id.martesText)
        miercolesSelected = view.findViewById(R.id.miercolesText)
        juevesSelected = view.findViewById(R.id.juevesText)
        viernesSelected = view.findViewById(R.id.viernesText)
        sabadoSelected = view.findViewById(R.id.sabadoText)
        domingoSelected = view.findViewById(R.id.domingoText)
        guardarSelected = view.findViewById(R.id.chipGuardar)
        nombrePastilla = view.findViewById(R.id.pastillaInsertada)
        tipoPastilla = view.findViewById(R.id.numeroPastillero)
        atrasSelected = view.findViewById(R.id.chipAtras)

        horaPicker.setOnClickListener { showTimePickerDialog() }
        lunesSelected.setOnClickListener {
            lunes = toggleBold(lunesSelected) //false = activo //true = no activo
        }
        martesSelected.setOnClickListener {
            martes = toggleBold(martesSelected) //false = activo //true = no activo
        }
        miercolesSelected.setOnClickListener {
            miercoles = toggleBold(miercolesSelected) //false = activo //true = no activo
        }
        juevesSelected.setOnClickListener {
            jueves = toggleBold(juevesSelected) //false = activo //true = no activo
        }
        viernesSelected.setOnClickListener {
            viernes = toggleBold(viernesSelected) //false = activo //true = no activo
        }
        sabadoSelected.setOnClickListener {
            sabado = toggleBold(sabadoSelected) //false = activo //true = no activo
        }
        domingoSelected.setOnClickListener {
            domingo = toggleBold(domingoSelected) //false = activo //true = no activo
        }

        guardarSelected.setOnClickListener{
            uploadFirebase()

            findNavController().navigate(R.id.timeFragment)
        }

        atrasSelected.setOnClickListener{

            findNavController().navigate(R.id.timeFragment)
        }



        return view
    }
    private fun toggleBold(textView: TextView): Boolean {
        val currentTypeface = textView.typeface
        val isBold = currentTypeface.isBold

        val newTypeface = if (isBold) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL), Typeface.NORMAL)
            false // Devuelve false si el texto se cambió a normal
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD), Typeface.BOLD)
            true // Devuelve true si el texto se cambió a negrita
        }

        return isBold
    }

    private fun uploadFirebase(){
        val database = FirebaseDatabase.getInstance()
        if(lunesSelected.typeface.isBold){
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/lunes")
            a.setValue(true)

            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/lunes/pastilla-1/horario")
            b.setValue(hour.toString())

            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/lunes/pastilla-1/nombre")
            c.setValue(nombre.toString())

        }else{
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/lunes")
            a.setValue(false)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/lunes/pastilla-1/horario")
            b.setValue("")
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/lunes/pastilla-1/nombre")
            c.setValue("")
        }

        if(martesSelected.typeface.isBold){
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/martes")
            a.setValue(true)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/martes/pastilla-1/horario")
            b.setValue(hour.toString())
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/martes/pastilla-1/nombre")
            c.setValue(nombre.toString())


        }else{
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/martes")
            a.setValue(false)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/martes/pastilla-1/horario")
            b.setValue("")
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/martes/pastilla-1/nombre")
            c.setValue("")
        }

        if(miercolesSelected.typeface.isBold){
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/miercoles")
            a.setValue(true)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/miercoles/pastilla-1/horario")
            b.setValue(hour.toString())
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/miercoles/pastilla-1/nombre")
            c.setValue(nombre.toString())

        }else{
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/miercoles")
            a.setValue(false)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/miercoles/pastilla-1/horario")
            b.setValue("")
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/miercoles/pastilla-1/nombre")
            c.setValue("")
        }

        if(juevesSelected.typeface.isBold){
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/jueves")
            a.setValue(true)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/jueves/pastilla-1/horario")
            b.setValue(hour.toString())
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/jueves/pastilla-1/nombre")
            c.setValue(nombre.toString())
        }else{
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/jueves")
            a.setValue(false)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/jueves/pastilla-1/horario")
            b.setValue("")
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/jueves/pastilla-1/nombre")
            c.setValue("")
        }

        if(viernesSelected.typeface.isBold){
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/viernes")
            a.setValue(true)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/viernes/pastilla-1/horario")
            b.setValue(hour.toString())
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/viernes/pastilla-1/nombre")
            c.setValue(nombre.toString())
        }else{
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/viernes")
            a.setValue(false)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/viernes/pastilla-1/horario")
            b.setValue("")
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/viernes/pastilla-1/nombre")
            c.setValue("")
        }

        if(sabadoSelected.typeface.isBold){
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/sabado")
            a.setValue(true)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/sabado/pastilla-1/horario")
            b.setValue(hour.toString())
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/sabado/pastilla-1/nombre")
            c.setValue(nombre.toString())
        }else{
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/sabado")
            a.setValue(false)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/sabado/pastilla-1/horario")
            b.setValue("")
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/sabado/pastilla-1/nombre")
            c.setValue("")
        }

        if(domingoSelected.typeface.isBold){
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/domingo")
            a.setValue(true)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/domingo/pastilla-1/horario")
            b.setValue(hour.toString())
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/domingo/pastilla-1/nombre")
            c.setValue(nombre.toString())

        }else{
            val a = database.getReference("usuarios/valenpitocco/dias-habilitados/domingo")
            a.setValue(false)
            val hour = horaPicker.text
            val b = database.getReference("usuarios/valenpitocco/pastillas-dia/domingo/pastilla-1/horario")
            b.setValue("")
            val nombre = nombrePastilla.text
            val c = database.getReference("usuarios/valenpitocco/pastillas-dia/domingo/pastilla-1/nombre")
            c.setValue("")
        }




    }


    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(parentFragmentManager, "timePicker")

    }
    private fun onTimeSelected(time: String) {
        horaPicker.setText("$time")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TimeSetViewModel::class.java)
        // TODO: Use the ViewModel
    }



    }


