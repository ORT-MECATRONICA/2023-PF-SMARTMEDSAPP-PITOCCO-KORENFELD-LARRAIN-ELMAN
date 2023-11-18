package com.smartmeds.smartmeds

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.*


class initUserFragment : Fragment() {

    companion object {
        fun newInstance() = initUserFragment()
    }

    private lateinit var viewModel: InitUserViewModel
    private lateinit var mailRecibido: shareMail
    private lateinit var firestore: FirebaseFirestore
    private lateinit var nombreEditText: EditText
    private lateinit var apellidoEditText: EditText
    private lateinit var edadEditText: EditText
    private lateinit var usuarioEditText: EditText
    private lateinit var iniciarButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_init_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nombreEditText = view.findViewById(R.id.nombre)
        apellidoEditText = view.findViewById(R.id.apellido)
        edadEditText = view.findViewById(R.id.edad)
        usuarioEditText = view.findViewById(R.id.usuario)
        iniciarButton = view.findViewById(R.id.iniciar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[InitUserViewModel::class.java]
        firestore = FirebaseFirestore.getInstance()

        iniciarButton.setOnClickListener {
            guardarUsuarioFirestore()
        }

    }

    private fun guardarUsuarioFirestore() {
        val nombre = nombreEditText.text.toString()
        val apellido = apellidoEditText.text.toString()
        val edad = edadEditText.text.toString().toInt()
        val nuevoUsuario = usuarioEditText.text.toString()

        data class Usuario(
            val nombre: String,
            val apellido: String,
            val edad: Int,
            val usuario: String
        )

        val usuario = Usuario(
            nombre = nombre,
            apellido = apellido,
            edad = edad,
            usuario = nuevoUsuario
        )

        mailRecibido = ViewModelProvider(requireActivity()).get(shareMail::class.java)
        mailRecibido.dataMail.observe(viewLifecycleOwner) { data1 ->

            firestore.collection("usuarios").document(data1).set(usuario)

                .addOnSuccessListener { documentReference ->
                    // El documento se guardó exitosamente
                    showSnackbar("Usuario Creado! Inicie sesion para continuar...")
                    findNavController().navigate(R.id.welcomeFragment)
                    // Realizar acciones adicionales si es necesario
                }
                .addOnFailureListener { e ->
                    // Ocurrió un error al guardar el documento
                    showSnackbar("A ocurrido un error, porfavor vuelva a intentar")
                }

        }

        val database = FirebaseDatabase.getInstance()


        // Obtenemos una referencia a la ubicación de los datos que deseamos leer



        for (i in 1..7) {
            var dia = "domingo"

            if (i == 0) {
                dia = "domingo"
            }
            if (i == 1) {
                dia = "lunes"
            }
            if (i == 2) {
                dia = "martes"
            }
            if (i == 3) {
                dia = "miercoles"
            }
            if (i == 4) {
                dia = "jueves"
            }
            if (i == 5) {
                dia = "viernes"
            }
            if (i == 6) {
                dia = "sabado"
            }

            val a = database.getReference("usuarios/${usuario.usuario}/dias-habilitados/${dia}")
            a.setValue(false)

            val f = database.getReference("usuarios/${usuario.usuario}/pastillas-dia/${dia}/pastilla-1/horario")
            f.setValue("")
            val g = database.getReference("usuarios/${usuario.usuario}/pastillas-dia/${dia}/pastilla-1/nombre")
            g.setValue("")
            val h = database.getReference("usuarios/${usuario.usuario}/pastillas-dia/${dia}/pastilla-1/tipo")
            h.setValue(1)

        }

            val b = database.getReference("usuarios/${usuario.usuario}/info-home/apagar-alarma")
            b.setValue(false)

            val c = database.getReference("usuarios/${usuario.usuario}/info-home/pastillas-tomadas")
            c.setValue(0)

            val d = database.getReference("usuarios/${usuario.usuario}/info-home/siguiente-horario")
            d.setValue("")

            val e = database.getReference("usuarios/${usuario.usuario}/info-home/siguiente-pastilla")
            e.setValue("")

        val f = database.getReference("usuarios/${usuario.usuario}/pastillas-tomadas")
        f.setValue(0)

    }




    private fun showSnackbar(message: String) {
        view?.let { rootView ->
            Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
        }
    }

}

/* LEER FIREBASE
        // Agregamos un ValueEventListener para escuchar los cambios en los datos
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Aquí se manejan los cambios en los datos
                // dataSnapshot contiene los datos que se obtuvieron desde Firebase
                // Para obtener un valor específico, podemos utilizar getValue()
                val valor = dataSnapshot.getValue(String::class.java)
                println("El valor es: $valor")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Aquí se maneja el error en caso de que la operación sea cancelada o falle.
            }
        })*/
