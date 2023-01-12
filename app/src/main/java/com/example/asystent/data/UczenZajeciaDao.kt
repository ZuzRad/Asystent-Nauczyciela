package com.example.asystent.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent.model.UczenZajecia

@Dao
interface UczenZajeciaDao {
    @Query("SELECT * FROM uczen_zajecia_table ORDER BY id ASC")
    fun wyswietlUczniowZajecia2(): List<UczenZajecia>

    @Query("SELECT * FROM uczen_zajecia_table WHERE id_zajec LIKE :x ORDER BY id ASC")
    fun wyswietlUczniowZajecia3(x: Int?): List<UczenZajecia>

    @Query("SELECT * FROM uczen_zajecia_table WHERE id_zajec LIKE :data ORDER BY id ASC")
    fun wyswietlUczniowZajecia(data: Int?): LiveData<List<UczenZajecia>>

    @Query("DELETE FROM uczen_zajecia_table")
    suspend fun usunUczniowZajecia()

    @Insert
    suspend fun insert(uczenZajecia: UczenZajecia)
}