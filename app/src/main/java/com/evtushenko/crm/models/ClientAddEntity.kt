package com.evtushenko.crm.models


/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
data class ClientAddEntity(
    val name: String,
    val lastName: String,
    val phone: String,
    val birthday: String,
    val gender: ClientGender = ClientGender.MALE
)
