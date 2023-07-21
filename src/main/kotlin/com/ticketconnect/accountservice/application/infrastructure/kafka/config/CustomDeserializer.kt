package com.ticketconnect.accountservice.application.infrastructure.kafka.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.ticketconnect.accountservice.application.infrastructure.kafka.message.AccountMessage
import org.apache.kafka.common.serialization.Deserializer

class CustomDeserializer(
    private val objectMapper: ObjectMapper
) : Deserializer<AccountMessage> {

    override fun deserialize(topic: String?, data: ByteArray?): AccountMessage {
        return runCatching {
            objectMapper.readValue(data, AccountMessage::class.java)
        }.onFailure {
            throw RuntimeException("Error deserializing data $data")
        }.getOrThrow()
    }
}