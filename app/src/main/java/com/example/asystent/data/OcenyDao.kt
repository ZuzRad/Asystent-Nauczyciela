package com.example.asystent.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystent.model.Oceny

@Dao
interface OcenyDao {
    @Query("SELECT * FROM oceny_table WHERE id_ucznia_ocena LIKE :uczen AND id_zajec_ocena LIKE :zajecia ORDER BY id ASC")
    fun wyswietlOceny(uczen: Int?,zajecia: Int?): LiveData<List<Oceny>>

    @Query("DELETE FROM oceny_table")
    suspend fun usunOceny()

    @Insert
    suspend fun insert(oceny: Oceny)
}