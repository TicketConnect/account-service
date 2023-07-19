package com.ticketconnect.accountservice.infrastructure.document

import com.ticketconnect.accountservice.entity.Account
import com.ticketconnect.accountservice.entity.valueobject.Cellphone
import com.ticketconnect.accountservice.entity.valueobject.Email
import com.ticketconnect.accountservice.entity.valueobject.Password
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document
data class AccountDocument(
    @Indexed(unique = true)
    var accountNumber: String,
    @Field("name")
    val name: String,
    @Field("last_name")
    val lastName: String,
    @Field("email")
    val email: String,
    @Field("cellphone")
    val cellphone: String,
    @Field("gender")
    val gender: String,
    @Field("password")
    var password: String
) {
    companion object {
        fun AccountDocument.toDomain(): Account = (
                Account(
                    id = UUID.fromString(this.accountNumber),
                    name = this.name,
                    lastName = this.lastName,
                    email = Email(this.email),
                    cellphone = Cellphone(this.cellphone),
                    gender = gender,
                    password = Password(this.password)
                ))

        fun Account.toDocument(): AccountDocument = (
                AccountDocument(
                    accountNumber =  this.id.toString(),
                    name = this.name,
                    lastName = this.lastName,
                    email = this.email.getEmailValue(),
                    cellphone = this.cellphone.getCellphoneValue(),
                    gender = gender,
                    password = this.password.getPasswordValue()
                ))
    }
}
