package com.rand.datepickerdialoginsidealertdialog.model

data class Person(private val name: String, private val citizenship : String,
                  private val birthDate : String) {
    val person get() = "Name: $name\nCitizenship: $citizenship\nBirthDate: $birthDate"
}
