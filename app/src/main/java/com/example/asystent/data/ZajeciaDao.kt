package com.example.asystent.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent.model.Zajecia

@Dao
interface ZajeciaDao {
    @Query("SELECT * FROM zajecia_table ORDER BY id ASC")
    fun wyswietlZajecia(): LiveData<List<Zajecia>>

    @Query("SELECT * FROM zajecia_table ORDER BY id ASC")
    fun wyswietlZajecia2(): List<Zajecia>

    @Query("DELETE FROM zajecia_table")
    suspend fun usunWszystkieZajecia()

    @Insert
    suspend fun insert(zajecia: Zajecia)
}