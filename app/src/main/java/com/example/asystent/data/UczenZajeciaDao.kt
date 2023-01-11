package com.example.asystent.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent.model.UczenZajecia

@Dao
interface UczenZajeciaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun dodajUczniaZajecia(uczenZajecia: UczenZajecia)

    @Query("SELECT * FROM uczen_zajecia_table ORDER BY id ASC")
    fun wyswietlUczniowZajecia2(): List<UczenZajecia>

    @Query("SELECT * FROM uczen_zajecia_table ORDER BY id ASC")
    fun wyswietlUczniowZajecia(): LiveData<List<UczenZajecia>>


    @Delete
    suspend fun usunUczniaZajecia(uczenZajecia: UczenZajecia)

    @Query("DELETE FROM uczen_zajecia_table")
    suspend fun usunUczniowZajecia()


    @Insert
    suspend fun insert(uczenZajecia: UczenZajecia)
}