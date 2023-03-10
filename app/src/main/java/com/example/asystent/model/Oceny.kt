package com.example.asystent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "oceny_table")
class Oceny (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name="id_ucznia_ocena") var id_ucznia_ocena: Int?,
    @ColumnInfo(name="id_zajec_ocena") var id_zajec_ocena: Int?,
    @ColumnInfo(name="ocena")var ocena: Int
) {
    @Ignore
    constructor(id_ucznia_ocena: Int?,id_zajec_ocena: Int?,ocena: Int) : this (null, id_ucznia_ocena,id_zajec_ocena, ocena)
}