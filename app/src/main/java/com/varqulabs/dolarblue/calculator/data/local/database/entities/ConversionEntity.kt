package com.varqulabs.dolarblue.calculator.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.varqulabs.dolarblue.history.domain.model.Conversion
import java.time.LocalDateTime

@Entity(tableName = "conversion_table")
data class ConversionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "pesosBob") val pesosBob: String,
    @ColumnInfo(name = "pesosArg") val pesosArg: String,
    @ColumnInfo(name = "dollar") val dollar: String,
    @ColumnInfo(name = "date") val date: LocalDateTime, //GUARDAR EN TIMESTAP EN FIREBASE
    @ColumnInfo(name = "currentExchangeId") val currentExchangeId: Int
)

fun ConversionEntity.mapToModel() = Conversion(
    id = id,
    pesosBob = pesosBob,
    pesosArg = pesosArg,
    dollar = dollar,
    date = date,
    currentExchangeId = currentExchangeId
)