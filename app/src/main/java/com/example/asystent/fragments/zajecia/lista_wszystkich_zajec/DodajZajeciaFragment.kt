package com.example.asystent.fragments.zajecia.lista_wszystkich_zajec

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.model.Zajecia
import com.example.asystent.databinding.FragmentDodajZajeciaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


class DodajZajeciaFragment:Fragment() {
    private var _binding: FragmentDodajZajeciaBinding? = null
    private val binding get() = _binding!!
    private val dni = arrayOf("poniedziałek", "wtorek", "środa", "czwartek", "piątek")
    private val godziny = arrayOf("8:00 - 9:00", "9:15 - 10:15", "10:30 - 11:30", "11:45 - 12:45", "13:00 - 14:00", "14:15 - 15:15")
    private lateinit var appDatabase: AppDatabase


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDodajZajeciaBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getDatabase(requireContext())
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dzien =""
        var godzina=""
        val nazwa = view.findViewById<EditText>(R.id.editText_nazwaZajec)

        val spinner_dzien = view.findViewById<Spinner>(R.id.spinner_dzien)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dni)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_dzien.adapter = adapter
        spinner_dzien.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                dzien = parent?.getItemAtPosition(position).toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val spinner_godzina = view.findViewById<Spinner>(R.id.spinner_blok)
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, godziny)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_godzina.adapter = adapter2
        spinner_godzina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                godzina = parent?.getItemAtPosition(position).toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val button_dodaj = view.findViewById<Button>(R.id.button_dodajZajecia2)
        button_dodaj.setOnClickListener{



            runBlocking(Dispatchers.IO) {
                var dane = Zajecia("","","")
                //dane = Uczen(editText_imie.text.toString(), editText_nazwisko.text.toString(), editText_nr.text.toString())
                dane = Zajecia(nazwa.text.toString(), dzien, godzina)
                appDatabase.zajeciaDao().insert(dane)
            }
            Toast.makeText(context, "Dodano zajęcia", Toast.LENGTH_SHORT).show()
            nazwa.setText("")
//            val fragment: Fragment = ZajeciaFragment()
//            val fragmentManager = requireActivity().supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.frameLayout, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//            requireActivity().title = "Zajęcia"
        }
    }
}