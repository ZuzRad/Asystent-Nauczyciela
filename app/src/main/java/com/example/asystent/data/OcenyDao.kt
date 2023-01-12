package com.example.asystent.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent.model.Oceny

@Dao
interface OcenyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun dodajOcene(oceny: Oceny)

    @Query("SELECT * FROM oceny_table ORDER BY id ASC")
    fun wyswietlOceny2(): List<Oceny>

    @Query("SELECT * FROM oceny_table ORDER BY id ASC")
    fun wyswietlOceny(): LiveData<List<Oceny>>


    @Query("DELETE FROM oceny_table")
    suspend fun usunOceny()

    @Insert
    suspend fun insert(oceny: Oceny)
}