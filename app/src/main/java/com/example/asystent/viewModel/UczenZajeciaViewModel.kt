package com.example.asystent.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystent.data.AppDatabase
import com.example.asystent.model.Uczen
import com.example.asystent.model.UczenZajecia
import com.example.asystent.repository.UczenRepository
import com.example.asystent.repository.ZajeciUczenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UczenZajeciaViewModel(application: Application) : AndroidViewModel(application) {
   // val wyswietl_wszystko: LiveData<List<UczenZajecia>>
    private val repository: ZajeciUczenRepository
    init{
        val uczenZajeciaDao = AppDatabase.getDatabase(application).uczenZajeciaDao()
        repository = ZajeciUczenRepository(uczenZajeciaDao)
        //wyswietl_wszystko = uczenZajeciaDao.wyswietlUczniowZajecia()

    }

}