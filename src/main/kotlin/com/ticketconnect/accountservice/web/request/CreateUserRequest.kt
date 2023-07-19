package com.ticketconnect.accountservice.web.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.ticketconnect.accountservice.infrastructure.document.AccountDocument
import jakarta.validation.constraints.NotBlank
import java.util.*

data class CreateUserRequest(
    @JsonProperty("name")
    @field:NotBlank
    val name: String,
    @JsonProperty("last_name")
    @field:NotBlank
    val lastName: String,
    @JsonProperty("email")
    @field:NotBlank
    val email: String,
    @JsonProperty("cellphone")
    @field:NotBlank
    val cellphone: String,
    @JsonProperty("password")
    @field:NotBlank
    var password: String,
    @JsonProperty("gender")
    @field:NotBlank
    val gender: String
) {

    companion object {
        fun CreateUserRequest.toDocument() = AccountDocument (
            accountNumber = UUID.randomUUID().toString(),
            name = this.name,
            lastName = this.lastName,
            email = this.email,
            cellphone = this.cellphone,
            gender = this.gender,
            password = this.password
        )
    }
}
