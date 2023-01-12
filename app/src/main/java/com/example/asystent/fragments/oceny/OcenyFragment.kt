package com.example.asystent.fragments.oceny

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.databinding.FragmentOcenyBinding
import com.example.asystent.databinding.FragmentWybraneZajeciaBinding
import com.example.asystent.fragments.zajecia.wybrane_zajecia.UczniowieZajeciaAdapter
import com.example.asystent.fragments.zajecia.wybrane_zajecia.WybraneZajeciaFragment
import com.example.asystent.model.Oceny
import com.example.asystent.model.Uczen
import com.example.asystent.model.UczenZajecia
import com.example.asystent.model.Zajecia
import com.example.asystent.viewModel.OcenyViewModel
import com.example.asystent.viewModel.UczenViewModel
import com.example.asystent.viewModel.UczenZajeciaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fragment analogiczny do wybranezajeciafragment
//bedzie przyjmowal id ucznia i id zajec aby w spinnerze dodac ocene do tabeli i w recyclerview wyswietli sie ocena ucznia z tego przedmiotu
class OcenyFragment:Fragment() {
    private lateinit var appDatabase: AppDatabase
    private var _binding: FragmentOcenyBinding? = null
    private val binding get() = _binding!!
    var inputId: Int? = null
    var input_id_ucznia: Int? = null
    var input_id_zajec: Int? = null
    private val listaOcen = arrayOf("Dodaj Ocenę",1,2,3,4,5,6)
    private lateinit var ocenyViewModel: OcenyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentOcenyBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getDatabase(requireContext())
        inputId = arguments?.getInt("input_id")
        input_id_ucznia = arguments?.getInt("input_id_ucznia")
        input_id_zajec = arguments?.getInt("input_id_zajec")


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner_oceny = view.findViewById<Spinner>(R.id.spinner_oceny)

                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listaOcen)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner_oceny.adapter = adapter
                spinner_oceny.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if(position>0){
                            runBlocking(Dispatchers.IO) {
                                var dane = Oceny(0,0,0)
                                dane = Oceny(input_id_ucznia, input_id_zajec, listaOcen[position].toString().toInt())
                                appDatabase.ocenyDao().insert(dane)
                            }
                        }
                    }override fun onNothingSelected(parent: AdapterView<*>?) {}
                }

        val uczen_text =view.findViewById<TextView>(R.id.textView_uczen)
        val zajecia_text =view.findViewById<TextView>(R.id.textView_zajecia)
        runBlocking(Dispatchers.IO) {
            launch {
                var listaUczniow: List<Uczen> = appDatabase.uczenDao().wyswietlUczniow2()
                var listaZajec: List<Zajecia> = appDatabase.zajeciaDao().wyswietlZajecia2()
                for(x in listaUczniow.indices){
                    if(input_id_ucznia == listaUczniow[x].id){
                        uczen_text.setText(listaUczniow[x].imie+" "+listaUczniow[x].nazwisko+" "+listaUczniow[x].nr)
                    }
                }
                for(x in listaZajec.indices){
                    if(input_id_zajec == listaZajec[x].id){
                        zajecia_text.setText(listaZajec[x].nazwa+" "+listaZajec[x].dzien+" "+listaZajec[x].godzina)
                    }
                }
            }
        }


        //recycleview
        val adapter2 = ListaOcenAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.lista_oceny)
        recyclerView.adapter = adapter2
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel
        ocenyViewModel = ViewModelProvider(this).get(OcenyViewModel::class.java)
        appDatabase.ocenyDao().wyswietlOceny(input_id_ucznia, input_id_zajec).observe(viewLifecycleOwner, Observer { oceny ->
            adapter2.setData(oceny)
        })
    }
}