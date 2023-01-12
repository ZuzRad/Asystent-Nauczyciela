package com.example.asystent.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.asystent.data.AppDatabase
import com.example.asystent.repository.OcenyRepository

class OcenyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: OcenyRepository
    init{
        val ocenyDao = AppDatabase.getDatabase(application).ocenyDao()
        repository = OcenyRepository(ocenyDao)
    }

}