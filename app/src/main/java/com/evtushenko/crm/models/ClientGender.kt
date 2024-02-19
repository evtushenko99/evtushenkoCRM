package com.evtushenko.crm.models

/**
 * TODO - Описание класса
 *
 * @author Евтушенко Максим 18.02.2024
 */
enum class ClientGender {

    FEMALE, MALE;

    companion object {
        fun String.toGender(): ClientGender =
            entries.find { it.name == this } ?: FEMALE
    }

}