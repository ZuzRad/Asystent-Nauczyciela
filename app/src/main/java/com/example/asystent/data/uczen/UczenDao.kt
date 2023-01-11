package com.example.asystent.data.uczen

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UczenDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun dodajUcznia(uczen: Uczen)

    @Query("SELECT * FROM uczen_table ORDER BY id ASC")
    fun wyswietlUczniow(): LiveData<List<Uczen>>

    @Query("SELECT imie FROM uczen_table")
    fun wyswietlImie(): String

    @Delete
    suspend fun usunUcznia(uczen: Uczen)

    @Query("DELETE FROM uczen_table")
    suspend fun usunUczniow()


    @Insert
    suspend fun insert(uczen: Uczen)
}