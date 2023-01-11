package com.example.asystent.data.zajecia

import androidx.lifecycle.LiveData


class ZajeciaRepository(private val zajeciaDao: ZajeciaDao) {
    val wyswietlZajecia: LiveData<List<Zajecia>> = zajeciaDao.wyswietlZajecia()

    suspend fun dodajZajecia(zajecia: Zajecia){
        zajeciaDao.dodajZajecia(zajecia)
    }

    suspend fun usunZajecia(zajecia: Zajecia){
        zajeciaDao.usunZajecia(zajecia)
    }

    suspend fun usunWszystkiezajecia(){
        zajeciaDao.wyswietlZajecia()
    }
}