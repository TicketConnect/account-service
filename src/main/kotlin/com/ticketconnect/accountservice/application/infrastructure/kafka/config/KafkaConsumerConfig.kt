package com.ticketconnect.accountservice.application.infrastructure.kafka.config

import com.ticketconnect.accountservice.application.infrastructure.kafka.group.GroupId
import com.ticketconnect.accountservice.application.infrastructure.kafka.message.AccountMessage
import org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
@EnableKafka
class KafkaConsumerConfig {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, AccountMessage> {
        val props = HashMap<String, Any>()
        props[BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[GROUP_ID_CONFIG] = GroupId.ACCOUNT.groupName
        props[KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[VALUE_DESERIALIZER_CLASS_CONFIG] = CustomDeserializer::class.java
        props[AUTO_OFFSET_RESET_CONFIG] = "earliest"
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConsumerFactory<String, AccountMessage> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, AccountMessage> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        return consumerFactory()
    }
}