package com.ticketconnect.accountservice.application.infrastructure.kafka.config

import com.ticketconnect.accountservice.application.infrastructure.kafka.topic.Topic
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.TopicBuilder

@Configuration
@EnableKafka
class TopicConfig {

    @Bean
    fun orderTopic(): NewTopic {
        return TopicBuilder.name(Topic.ACCOUNT.topicName)
            .partitions(1)
            .compact()
            .build()
    }
}