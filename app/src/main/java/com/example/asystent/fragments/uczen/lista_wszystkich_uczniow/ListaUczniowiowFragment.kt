package com.example.asystent.fragments.uczen.lista_wszystkich_uczniow

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.databinding.FragmentListaUczniowBinding
import com.example.asystent.viewModel.UczenViewModel


class ListaUczniowiowFragment:Fragment() {
    private lateinit var appDatabase: AppDatabase
    private var _binding: FragmentListaUczniowBinding? = null
    private val binding get() = _binding!!

    private lateinit var uczenViewmodel: UczenViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentListaUczniowBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getDatabase(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recycleview
        val adapter = ListaUczniowAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.uczniowie_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel
        uczenViewmodel = ViewModelProvider(this).get(UczenViewModel::class.java)
        uczenViewmodel.wyswietl_wszystko.observe(viewLifecycleOwner, Observer { uczen ->
            adapter.setData(uczen)
        })


        val button_dodaj = view.findViewById<Button>(R.id.button_dodajUcznia)
        button_dodaj.setOnClickListener{
            val fragment: Fragment = DodajUczniaFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            requireActivity().title = "Dodaj ucznia"
        }

    }

}