package com.example.openweathermaptest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.openweathermaptest.databinding.FragmentEmptyBinding


class EmptyFragment : Fragment() {

    private var _binding: FragmentEmptyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmptyBinding.inflate(inflater,container,false)
        return binding.root
    }








    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}