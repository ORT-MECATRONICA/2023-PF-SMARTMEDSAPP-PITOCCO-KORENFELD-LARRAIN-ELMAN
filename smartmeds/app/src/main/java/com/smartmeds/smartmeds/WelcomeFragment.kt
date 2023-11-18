package com.smartmeds.smartmeds

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment() {


    private lateinit var viewModel: WelcomeViewModel

    lateinit var botonRegistrarse: Button
    lateinit var botonLogin : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        botonRegistrarse = view.findViewById((R.id.botonRegistrarse))
        botonLogin = view.findViewById((R.id.botonInicioSesion))

        botonRegistrarse.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        botonLogin.setOnClickListener{
            findNavController().navigate(R.id.loginFragment)
        }




        return view

    }
}