package com.ticketconnect.accountservice.application.infrastructure.kafka.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.ticketconnect.accountservice.application.infrastructure.kafka.message.AccountMessage
import org.apache.kafka.common.serialization.Serializer

class CustomSerializer(
    private val objectMapper: ObjectMapper
) : Serializer<AccountMessage> {
    override fun serialize(topic: String?, data: AccountMessage?): ByteArray {
        return runCatching {
            objectMapper.writeValueAsBytes(data)
        }.onFailure {
            throw RuntimeException("Error serializing data $data")
        }.getOrThrow()
    }
}