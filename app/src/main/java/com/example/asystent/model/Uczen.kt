package com.example.asystent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "uczen_table")
data class Uczen(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name="imie") var imie: String,
    @ColumnInfo(name="nazwisko")var nazwisko: String,
    @ColumnInfo(name="nr")var nr: Int
) {
    @Ignore
    constructor(imie: String,nazwisko: String, nr: Int) : this (null, imie,nazwisko, nr)
}