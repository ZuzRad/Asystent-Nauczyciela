package com.example.asystent.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystent.data.AppDatabase
import com.example.asystent.repository.UczenRepository
import com.example.asystent.model.Uczen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UczenViewModel(application: Application) : AndroidViewModel(application) {
    val wyswietl_wszystko: LiveData<List<Uczen>>
    private val repository: UczenRepository
    init{
        val uczenDao = AppDatabase.getDatabase(application).uczenDao()
        repository = UczenRepository(uczenDao)
        wyswietl_wszystko = repository.wyswiwtl_wszystko

    }

    fun dodajUcznia(uczen: Uczen){
        viewModelScope.launch(Dispatchers.IO){
            repository.dodajU(uczen)
        }
    }

    fun usunUcznia(uczen: Uczen){
        viewModelScope.launch(Dispatchers.IO) {
            repository.usunUcznia(uczen)
        }
    }

    fun usunUcznoiw(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.usunUczniow()
        }
    }
}