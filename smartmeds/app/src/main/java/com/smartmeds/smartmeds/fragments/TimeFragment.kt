package com.smartmeds.smartmeds.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.smartmeds.smartmeds.R
import com.smartmeds.smartmeds.fragments.adapter.horariosAdapter


class TimeFragment : Fragment() {

    companion object {
        fun newInstance() = TimeFragment()
    }

    private lateinit var viewModel: TimeViewModel
    lateinit var addButton : FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_time, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.RecyclerTime)
        addButton = view.findViewById<FloatingActionButton>(R.id.addButtonID) // Asigna addButton con findViewById

        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_timeFragment_to_timeSetFragment)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = horariosAdapter(HorariosProvider.alarmasList)





        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TimeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}