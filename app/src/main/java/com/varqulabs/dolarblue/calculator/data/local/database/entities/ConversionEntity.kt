package com.varqulabs.dolarblue.calculator.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "conversion_table")
data class ConversionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "pesosBob") val pesosBob: String,
    @ColumnInfo(name = "pesosArg") val pesosArg: String,
    @ColumnInfo(name = "dollar") val dollar: String,
    @ColumnInfo(name = "date") val date: LocalDateTime, //GUARDAR EN TIMESTAP EN FIREBASE
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean,
    @ColumnInfo(name = "currentExchangeId") val currentExchangeId: Int
)