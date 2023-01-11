package com.example.asystent.data.uczen

import androidx.lifecycle.LiveData

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