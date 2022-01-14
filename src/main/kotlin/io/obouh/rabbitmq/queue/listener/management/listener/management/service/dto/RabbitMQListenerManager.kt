package io.obouh.rabbitmq.queue.listener.management.listener.management.service.dto

import org.springframework.amqp.rabbit.listener.MessageListenerContainer
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry
import org.springframework.stereotype.Service

@Service
class RabbitMQListenerManager(val rabbitListenerEndpointRegistry: RabbitListenerEndpointRegistry) {

    fun stop(id : String){
       val listenerContainer : MessageListenerContainer = rabbitListenerEndpointRegistry.getListenerContainer(id);
        if(listenerContainer != null){
            listenerContainer.stop()
        }
    }

    fun start(id : String){
        val listenerContainer : MessageListenerContainer = rabbitListenerEndpointRegistry.getListenerContainer(id)
        if(listenerContainer != null){
            listenerContainer.start()
        }
    }

    fun stopAll() {
        rabbitListenerEndpointRegistry.stop();
    }

    fun startAll() {
        rabbitListenerEndpointRegistry.start();
    }

    fun fetchAllListenersState():Set<RabbitMQListener>{
        val rabbitMQListeners = HashSet<RabbitMQListener>()
        rabbitListenerEndpointRegistry.listenerContainerIds.stream().forEach { listenerId ->
            val rabbitMQListener = RabbitMQListener(listenerId,rabbitListenerEndpointRegistry.getListenerContainer(listenerId).isRunning)
            rabbitMQListeners.add(rabbitMQListener)
        }
        return rabbitMQListeners;
    }
}
