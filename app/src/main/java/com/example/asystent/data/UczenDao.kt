package com.example.asystent.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent.model.Uczen

@Dao
interface UczenDao {
    @Query("SELECT * FROM uczen_table ORDER BY id ASC")
    fun wyswietlUczniow2(): List<Uczen>

    @Query("SELECT * FROM uczen_table ORDER BY id ASC")
    fun wyswietlUczniow(): LiveData<List<Uczen>>

    @Query("DELETE FROM uczen_table")
    suspend fun usunUczniow()

    @Insert
    suspend fun insert(uczen: Uczen)
}