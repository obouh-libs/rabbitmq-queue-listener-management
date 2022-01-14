package io.obouh.rabbitmq.queue.listener.management.listener.management.rest

import io.obouh.rabbitmq.queue.listener.management.listener.management.service.dto.RabbitMQListener
import io.obouh.rabbitmq.queue.listener.management.listener.management.service.RabbitMQListenerManager
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rabbitmq/listeners")
class RabbitMQListenerController(val rabbitMQListenerManager: RabbitMQListenerManager) {

    @PostMapping("/{id}/stop")
    fun stop(@PathVariable id:String) = rabbitMQListenerManager.stop(id)

    @PostMapping("/{id}/start")
    fun start(@PathVariable id:String) = rabbitMQListenerManager.start(id)

    @PostMapping("/stop")
    fun stopAll() = rabbitMQListenerManager.stopAll()

    @PostMapping("/start")
    fun startAll() = rabbitMQListenerManager.startAll()

    @GetMapping("/stats")
    fun fetchListenersStates():Set<RabbitMQListener> = rabbitMQListenerManager.fetchAllListenersState()
}