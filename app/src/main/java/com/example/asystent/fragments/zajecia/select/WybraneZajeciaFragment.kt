package com.example.asystent.fragments.zajecia.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.asystent.R
import com.example.asystent.databinding.FragmentWybraneZajeciaBinding

class WybraneZajeciaFragment:Fragment() {
    private var _binding: FragmentWybraneZajeciaBinding? = null
    private val binding get() = _binding!!
    var inputId: Int? = null
    var inputNazwa: String = ""
    var inputDzien: String = ""
    var inputGodzina: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentWybraneZajeciaBinding.inflate(inflater, container, false)

        inputId = arguments?.getInt("input_id")
        inputNazwa = arguments?.getString("input_nazwa").toString()
        inputDzien = arguments?.getString("input_dzien").toString()
        inputGodzina = arguments?.getString("input_godzina").toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val zajecia_text =view.findViewById<TextView>(R.id.text_zajecia)
        zajecia_text.setText(inputNazwa+"  "+inputDzien+"  "+inputGodzina)


    }


}