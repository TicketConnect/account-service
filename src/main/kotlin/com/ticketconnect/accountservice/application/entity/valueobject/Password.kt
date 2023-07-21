package com.ticketconnect.accountservice.application.entity.valueobject

data class Password(private val password: String) {

    init {
        val pattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9\\W]).{8,}$")
        val isValid = pattern.matches(password)

        require(isValid) { "The password is not valid" }
    }

    fun toPassword(password: String): Password = (
        Password(password)
    )

    fun getPasswordValue(): String = (
        this.password
    )
}
