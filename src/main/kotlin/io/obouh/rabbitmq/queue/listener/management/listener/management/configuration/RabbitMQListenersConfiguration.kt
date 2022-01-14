package io.obouh.rabbitmq.queue.listener.management.listener.management.configuration

import io.obouh.rabbitmq.queue.listener.management.listener.management.rest.RabbitMQListenerController
import io.obouh.rabbitmq.queue.listener.management.listener.management.service.RabbitMQListenerManager
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQListenersConfiguration(@Autowired val rabbitListenerEndpointRegistry: RabbitListenerEndpointRegistry) {

    @Bean
    fun rabbitMQListenerManager():RabbitMQListenerManager {
        return RabbitMQListenerManager(rabbitListenerEndpointRegistry);
    }

    @Bean
    fun rabbitMQListenerController():RabbitMQListenerController {
        return RabbitMQListenerController(rabbitMQListenerManager())
    }
}