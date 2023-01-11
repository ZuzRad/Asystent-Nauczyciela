package com.example.asystent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.asystent.data.AppDatabase
import com.example.asystent.data.uczen.Uczen
import com.example.asystent.databinding.FragmentStronaGlownaBinding
import com.example.asystent.fragments.uczen.UczniowieFragment
import com.example.asystent.fragments.zajecia.ZajeciaFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class StronaGlownaFragment : Fragment() {
    private lateinit var appDatabase: AppDatabase
    private var _binding: FragmentStronaGlownaBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStronaGlownaBinding.inflate(inflater, container, false)
        appDatabase = AppDatabase.getDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val button_wyczysc = view.findViewById<Button>(R.id.button_wyczysc)
        button_wyczysc.setOnClickListener{
            runBlocking(Dispatchers.IO) {
                appDatabase.uczenDao().usunUczniow()
                appDatabase.zajeciaDao().usunWszystkieZajecia()
            }
            Toast.makeText(context, "Wyczyszczono dane", Toast.LENGTH_SHORT).show()
        }

    }
}