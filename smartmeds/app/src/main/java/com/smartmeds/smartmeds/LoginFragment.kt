package com.smartmeds.smartmeds

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    lateinit var email : EditText
    lateinit var clave : EditText
    lateinit var botonEntrar : Button
    private lateinit var auth : FirebaseAuth
    lateinit var datoEmail : String
    lateinit var datoPass : String
    lateinit var guardarUsuario : CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botonEntrar = view.findViewById(R.id.botonEntrar)
        email = view.findViewById(R.id.emaiLogin)
        clave = view.findViewById(R.id.claveLogin)
        auth = FirebaseAuth.getInstance()
        guardarUsuario = view.findViewById(R.id.inicioCheck)


        botonEntrar.setOnClickListener{
            //datoEmail = email.text.toString()
            //datoPass = clave.text.toString()
            datoEmail = "valenpitocco@gmail.com"
            datoPass = "hola123"
            auth.signInWithEmailAndPassword(datoEmail, datoPass)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        showSnackbar("Accediendo")
                        val user = auth.currentUser
                        findNavController().navigate(R.id.appActivity)
                    } else {
                        // If sign in fails, display a message to the user.
                        showSnackbar("Usuario no encontrado, pruebe nuevamente")

                    }
                }
        }
        }

    private fun showSnackbar(message: String) {
        view?.let { rootView ->
            Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
        }
    }
    }



