package com.smartmeds.smartmeds.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartmeds.smartmeds.R

class hostTimeFragment : Fragment() {

    companion object {
        fun newInstance() = hostTimeFragment()
    }

    private lateinit var viewModel: HostTimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_host_time, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HostTimeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}