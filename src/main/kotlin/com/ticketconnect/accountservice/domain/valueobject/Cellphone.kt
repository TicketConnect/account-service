package com.ticketconnect.accountservice.domain.valueobject

data class Cellphone(private val cellphone: String) {

    init {
        val pattern = Regex("^\\(\\d{2}\\) 9\\d{4}-\\d{4}$")
        val isValid = pattern.matches(cellphone)

        require(isValid) { "The number: $cellphone is not valid" }
    }
    
    fun toCellphone(cellphone: String): Cellphone = (
        Cellphone(cellphone)
    )

    fun getCellphoneValue(): String = (
        this.cellphone
    )
}
