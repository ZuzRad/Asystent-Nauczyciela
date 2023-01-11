package com.example.asystent.repository.selected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.asystent.viewModel.UczenViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.databinding.FragmentWybraneZajeciaBinding
import com.example.asystent.fragments.uczen.list.ListaUczniowAdapter
import com.example.asystent.model.Uczen
import com.example.asystent.model.UczenZajecia
import com.example.asystent.model.Zajecia
import com.example.asystent.viewModel.UczenZajeciaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class WybraneZajeciaFragment:Fragment() {
    private lateinit var appDatabase: AppDatabase
    private var _binding: FragmentWybraneZajeciaBinding? = null
    private val binding get() = _binding!!
    var inputId: Int? = null
    var inputNazwa: String = ""
    var inputDzien: String = ""
    var inputGodzina: String = ""
    private lateinit var uczenViewmodel: UczenViewModel
    private lateinit var uczenZajeciaViewmodel: UczenZajeciaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentWybraneZajeciaBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getDatabase(requireContext())
        inputId = arguments?.getInt("input_id")
        inputNazwa = arguments?.getString("input_nazwa").toString()
        inputDzien = arguments?.getString("input_dzien").toString()
        inputGodzina = arguments?.getString("input_godzina").toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListaUczniowAdapter()

        val spinner_uczen = view.findViewById<Spinner>(R.id.spinner_uczen)
        uczenViewmodel = ViewModelProvider(this).get(UczenViewModel::class.java)
        runBlocking(Dispatchers.IO) {
            launch{
                var list:List<Uczen> = appDatabase.uczenDao().wyswietlUczniow2()
                var newlist = dataToString(list)
                val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, newlist)
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner_uczen.adapter = adapter2
                spinner_uczen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                       // if(position>0){
                            runBlocking(Dispatchers.IO) {
                                var dane = UczenZajecia(0,0)
                                dane = UczenZajecia(list[position].id, inputId)
                                appDatabase.uczenZajeciaDao().insert(dane)
//                                newlist.removeAt(position)
                            }
                       // }



                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }
        }


        val zajecia_text =view.findViewById<TextView>(R.id.text_zajecia)
        zajecia_text.setText(inputNazwa+"  "+inputDzien+"  "+inputGodzina)


        val cos = getParentFragmentManager().findFragmentById(R.id.wybrane_zajecia)
        //recycleview
        val adapter3 = UczniowieZajeciaAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.uczniowie2_list)
        recyclerView.adapter = adapter3
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //ViewModel
        uczenZajeciaViewmodel = ViewModelProvider(this).get(UczenZajeciaViewModel::class.java)
        appDatabase.uczenZajeciaDao().wyswietlUczniowZajecia(inputId).observe(viewLifecycleOwner, Observer { uczenZajecia ->
            adapter3.setData(uczenZajecia)
        })


        //do wyswietlenia w nowym recycleview wszytskich uczniów
//        //recycleview
//        val adapter4 = ListaUczniowAdapter()
//        val recyclerView2 = view.findViewById<RecyclerView>(R.id.uczniowie3_list)
//        recyclerView2.adapter = adapter4
//        recyclerView2.layoutManager = LinearLayoutManager(requireContext())
//
//
//        //ViewModel
//        uczenViewmodel = ViewModelProvider(this).get(UczenViewModel::class.java)
//        uczenViewmodel.wyswietl_wszystko.observe(viewLifecycleOwner, Observer { uczen ->
//            adapter4.setData(uczen)
//        })

    }

    fun getZajeciaId():Int?{
        var inputId: Int? = null
        inputId = arguments?.getInt("input_id")
        return inputId
    }

    fun dataToString(list: List<Uczen>): MutableList<Any> {
        var actual = ""
        var text: MutableList<Any> = mutableListOf()
        //text.add(actual)
        //actual=""
        for(x in list.indices){
            for(y in 0 .. 3){
                if(y==0){
                    actual += list[x].imie + " "
                }
                if(y==1) {
                    actual += list[x].nazwisko + " "
                }
                if(y==2){
                    actual += list[x].nr.toString() + " "
                }
            }
            text.add(actual)
            actual = ""

        }
        return text
    }
}