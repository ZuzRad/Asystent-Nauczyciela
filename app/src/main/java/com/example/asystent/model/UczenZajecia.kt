package com.example.asystent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "uczen_zajecia_table")
data class UczenZajecia (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name="id_ucznia") var id_ucznia: Int?,
    @ColumnInfo(name="id_zajec")var id_zajec: Int?,

) {
    @Ignore
    constructor(id_ucznia: Int?,id_zajec: Int?) : this (null, id_ucznia, id_zajec)
}