package com.example.asystent.fragments.zajecia.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.viewModel.ZajeciaViewModel
import com.example.asystent.databinding.FragmentZajeciaBinding
import com.example.asystent.fragments.zajecia.DodajZajeciaFragment

class ZajeciaFragment:Fragment() {
    private lateinit var appDatabase: AppDatabase
    private var _binding: FragmentZajeciaBinding? = null
    private val binding get() = _binding!!
    private lateinit var zajeciaViewmodel: ZajeciaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentZajeciaBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recycleview
        val adapter = ListaZajecAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.zajecia_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel
        zajeciaViewmodel = ViewModelProvider(this).get(ZajeciaViewModel::class.java)
        zajeciaViewmodel.wyswietl_wszystko.observe(viewLifecycleOwner, Observer { zajecia ->
            adapter.setData(zajecia)
        })


        val button_dodaj = view.findViewById<Button>(R.id.button_dodajZajecia)
        button_dodaj.setOnClickListener{
            val fragment: Fragment = DodajZajeciaFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            requireActivity().title = "Dodaj zajęcia"
        }
    }
//    override fun passData(position: Int, nazwa: String, dzien: String, godzina: String) {
//        val bundle = Bundle()
//        bundle.putInt("input_id", position)
//        bundle.putString("input_nazwa", nazwa)
//        bundle.putString("input_dzien", dzien)
//        bundle.putString("input_godzina", godzina)
//
//        val fragment: Fragment = WybraneZajeciaFragment()
//        fragment.arguments = bundle

//        val fragmentManager = requireActivity().supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameLayout, fragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
//        requireActivity().title = "Zajecia:"

//    }
}