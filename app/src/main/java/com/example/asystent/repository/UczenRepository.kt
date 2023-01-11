package com.example.asystent.repository

import androidx.lifecycle.LiveData
import com.example.asystent.data.UczenDao
import com.example.asystent.model.Uczen

class UczenRepository(private val uczenDao: UczenDao) {
    val wyswiwtl_wszystko: LiveData<List<Uczen>> = uczenDao.wyswietlUczniow()

    suspend fun dodajU(uczen: Uczen){
        uczenDao.dodajUcznia(uczen)
    }

    suspend fun usunUcznia(uczen: Uczen){
        uczenDao.usunUcznia(uczen)
    }

    suspend fun usunUczniow(){
        uczenDao.usunUczniow()
    }
}