package com.evtushenko.crm.models


/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 11.02.2024
 */
data class ClientItem(
    val id: Int,
    val title: String,
    val subTitle: String,
    val gender: ClientGender
)
