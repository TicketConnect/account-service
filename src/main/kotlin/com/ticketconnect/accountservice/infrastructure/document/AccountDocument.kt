package com.ticketconnect.accountservice.infrastructure.document

import com.ticketconnect.accountservice.domain.Account
import com.ticketconnect.accountservice.domain.valueobject.Cellphone
import com.ticketconnect.accountservice.domain.valueobject.Email
import com.ticketconnect.accountservice.domain.valueobject.Password
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*
import javax.annotation.processing.Generated

@Document
data class AccountDocument(
    @Id
    @Generated("uuid")
    @Indexed(unique = true)
    val id: UUID,
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
    val password: String
) {
    companion object {
        fun AccountDocument.toDomain(): Account = (
                Account(
                    id = this.id,
                    name = this.name,
                    lastName = this.lastName,
                    email = Email(this.email),
                    cellphone = Cellphone(this.cellphone),
                    gender = gender,
                    password = Password(this.password)
                ))

        fun Account.toDocument(): AccountDocument = (
                AccountDocument(
                    id = this.id,
                    name = this.name,
                    lastName = this.lastName,
                    email = this.email.getEmailValue(),
                    cellphone = this.cellphone.getCellphoneValue(),
                    gender = gender,
                    password = this.password.getPasswordValue()
                ))
    }
}
