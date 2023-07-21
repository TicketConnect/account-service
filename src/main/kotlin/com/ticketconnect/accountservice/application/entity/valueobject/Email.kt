package com.ticketconnect.accountservice.application.entity.valueobject

data class Email(private val email: String) {
    init {
        val pattern = Regex("^([\\w\\d+._-]+)@([\\w\\d.-]+)\\.([a-zA-Z]{2,})$")
        val isValid = pattern.matches(this.email)

        require(isValid) { "The email: $email is not valid" }
    }

    fun toEmail(email: String): Email = (
        Email(email)
    )

    fun getEmailValue(): String = (
        this.email
    )
}
