package com.example.asystent.data.zajecia

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "zajecia_table")
class Zajecia(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name="nazwa") var nazwa: String,
    @ColumnInfo(name="dzien")var dzien: String,
    @ColumnInfo(name="godzina")var godzina: String
) {
    @Ignore
    constructor(nazwa: String,dzien: String, godzina: String) : this (null, nazwa,dzien, godzina)
}