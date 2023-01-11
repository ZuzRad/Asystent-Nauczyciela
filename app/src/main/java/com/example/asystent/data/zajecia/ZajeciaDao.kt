package com.example.asystent.data.zajecia

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ZajeciaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun dodajZajecia(zajecia: Zajecia)

    @Query("SELECT * FROM zajecia_table ORDER BY id ASC")
    fun wyswietlZajecia(): LiveData<List<Zajecia>>

    @Query("SELECT nazwa FROM zajecia_table")
    fun wyswietlNazwe(): String

    @Delete
    suspend fun usunZajecia(zajecia: Zajecia)

    @Query("DELETE FROM zajecia_table")
    suspend fun usunWszystkieZajecia()


    @Insert
    suspend fun insert(zajecia: Zajecia)
}