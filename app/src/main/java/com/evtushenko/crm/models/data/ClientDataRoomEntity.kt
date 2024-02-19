package com.evtushenko.crm.models.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
@Entity(tableName = "clients")
data class ClientDataRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "phone_number") val phone: String,
    @ColumnInfo(name = "gender") val gender: String,
    val birthday: String
)
