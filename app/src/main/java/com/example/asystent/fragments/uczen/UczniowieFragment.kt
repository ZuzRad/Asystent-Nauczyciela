package com.example.asystent.fragments.uczen

import android.app.AlertDialog
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
import com.example.asystent.data.uczen.UczenViewModel
import com.example.asystent.databinding.FragmentUczniowieBinding


class UczniowieFragment:Fragment() {
    private lateinit var appDatabase: AppDatabase
    private var _binding: FragmentUczniowieBinding? = null
    private val binding get() = _binding!!

    private lateinit var uczenViewmodel: UczenViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentUczniowieBinding.inflate(inflater, container, false)
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
        //Add menu
//        setHasOptionsMenu(true)
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId == R.id.menu_delete){
////            deleteUczen()
//        }
//        return super.onOptionsItemSelected(item)
//    }

//    private fun deleteUczen(){
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setPositiveButton("Tak"){ _, _ ->
//            uczenViewmodel.usunUcznia(args.)
//        }
//        builder.setNegativeButton("Nie"){ _, _ -> }
//        builder.setTitle("Usuwanie")
//        builder.setMessage("Na pewno chcesz usunąć tego ucznia?")
//        builder.create().show()
//    }
}