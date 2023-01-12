package com.example.asystent.repository

import androidx.lifecycle.LiveData
import com.example.asystent.data.ZajeciaDao
import com.example.asystent.model.Zajecia


class ZajeciaRepository(private val zajeciaDao: ZajeciaDao) {
    val wyswietlZajecia: LiveData<List<Zajecia>> = zajeciaDao.wyswietlZajecia()
}