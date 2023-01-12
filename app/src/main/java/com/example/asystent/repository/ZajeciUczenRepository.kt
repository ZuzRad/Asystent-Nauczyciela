package com.example.asystent.repository

import androidx.lifecycle.LiveData
import com.example.asystent.data.UczenZajeciaDao
import com.example.asystent.data.ZajeciaDao
import com.example.asystent.model.UczenZajecia
import com.example.asystent.model.Zajecia

class ZajeciUczenRepository(private val uczenZajeciaDao: UczenZajeciaDao) {

    suspend fun dodajUczenZajecia(uczenZajecia: UczenZajecia){
        uczenZajeciaDao.dodajUczniaZajecia(uczenZajecia)
    }

    suspend fun usunUczenZajecia(uczenZajecia: UczenZajecia){
        uczenZajeciaDao.usunUczniaZajecia(uczenZajecia)
    }

    suspend fun usunWszystkieUczenzajecia(){
        uczenZajeciaDao.usunUczniowZajecia()
    }
}