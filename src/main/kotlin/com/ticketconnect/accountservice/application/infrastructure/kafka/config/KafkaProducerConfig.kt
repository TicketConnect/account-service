package com.ticketconnect.accountservice.application.infrastructure.kafka.config

import com.ticketconnect.accountservice.application.infrastructure.kafka.group.GroupId
import com.ticketconnect.accountservice.application.infrastructure.kafka.message.AccountMessage
import org.apache.kafka.clients.CommonClientConfigs.GROUP_ID_CONFIG
import org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
@EnableKafka
class KafkaProducerConfig(
    private val kafkaProperties: KafkaProperties
) {

    @Bean
    fun producerFactory(): ProducerFactory<String, AccountMessage> {
        return DefaultKafkaProducerFactory(configs())
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, AccountMessage> {
        return KafkaTemplate(producerFactory())
    }


    private fun configs(): HashMap<String, Any> {
        return hashMapOf(
            BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            GROUP_ID_CONFIG to GroupId.ACCOUNT.groupName,
            KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            VALUE_SERIALIZER_CLASS_CONFIG to CustomSerializer::class.java
        )
    }
}