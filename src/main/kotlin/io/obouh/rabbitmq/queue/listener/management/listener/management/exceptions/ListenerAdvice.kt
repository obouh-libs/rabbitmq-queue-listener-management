package io.obouh.rabbitmq.queue.listener.management.listener.management.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime


@ControllerAdvice
class ListenerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ListenerContainerNotFoundException::class)
    fun handleListenerContainerNotFoundException(
        ex: ListenerContainerNotFoundException, request: WebRequest
    ): ResponseEntity<Any?>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = ex.message
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }
}