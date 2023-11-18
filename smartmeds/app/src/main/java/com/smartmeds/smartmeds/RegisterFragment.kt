package com.smartmeds.smartmeds

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var mailEnviado: shareMail
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    lateinit var registerButton : Button
    lateinit var emailEditText : EditText
    lateinit var passwordEditText : EditText





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_register, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar Firebase Auth y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        registerButton = view.findViewById(R.id.botonRegistrarse)
        emailEditText = view.findViewById(R.id.emailRegister)
        passwordEditText = view.findViewById(R.id.claveRegister)

        // Configurar el click listener para el botón de registro
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(password.length < 6){
                showSnackbar("La contraseña debe ser de minimo 6 caracteres")
            }else{
                // Llamar a la función de registro
                registerUser(email, password)
            }


        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // El registro fue exitoso, puedes redirigir al usuario a la siguiente pantalla
                    showSnackbar("Usuario creado!")
                    mailEnviado = ViewModelProvider(requireActivity()).get(shareMail::class.java)
                    mailEnviado.dataMail.value = email
                    findNavController().navigate(R.id.initUserFragment)
                } else {
                    // Hubo un error durante el registro, puedes mostrar un mensaje de error al usuario
                    showSnackbar("Error de conexion, espere unos segundos y vuelva a intentar")
                }
            }
    }

    private fun showSnackbar(message: String) {
        view?.let { rootView ->
            Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}

