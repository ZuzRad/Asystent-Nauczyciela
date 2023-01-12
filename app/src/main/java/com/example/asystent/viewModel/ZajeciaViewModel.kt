package com.example.asystent.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystent.data.AppDatabase
import com.example.asystent.repository.ZajeciaRepository
import com.example.asystent.model.Zajecia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ZajeciaViewModel(application: Application):AndroidViewModel(application) {
    val wyswietl_wszystko: LiveData<List<Zajecia>>
    private val repository: ZajeciaRepository
    init{
        val zajeciaDao = AppDatabase.getDatabase(application).zajeciaDao()
        repository = ZajeciaRepository(zajeciaDao)
        wyswietl_wszystko = repository.wyswietlZajecia
    }
}