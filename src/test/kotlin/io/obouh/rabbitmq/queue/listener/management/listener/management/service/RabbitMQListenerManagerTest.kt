package io.obouh.rabbitmq.queue.listener.management.listener.management.service

import io.obouh.rabbitmq.queue.listener.management.listener.management.exceptions.ListenerContainerNotFound
import io.obouh.rabbitmq.queue.listener.management.listener.management.service.dto.RabbitMQListener
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.amqp.rabbit.listener.MessageListenerContainer
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry

@ExtendWith (MockitoExtension::class)
class RabbitMQListenerManagerTest {

    @Mock
    lateinit var rabbitListenerEndpointRegistry:RabbitListenerEndpointRegistry

    @InjectMocks
    lateinit var rabbitMQListenerManager: RabbitMQListenerManager

    @Test
    fun should_stop_rabbitmq_listener_by_id (){
        // Given
        val id = "listener_id"
        val messageListenerContainer:MessageListenerContainer = mock(MessageListenerContainer::class.java)
        `when`(rabbitListenerEndpointRegistry.getListenerContainer(eq(id))).thenReturn(messageListenerContainer)

        // When
        rabbitMQListenerManager.stop(id)

        // Then
        verify(messageListenerContainer, times(1)).stop()
    }

    @Test
    fun should_not_stop_rabbitmq_listener_by_id_when_not_found (){
        // Given && When && Then
        Assertions.assertThrows(ListenerContainerNotFound::class.java) {
            rabbitMQListenerManager.stop("not_found")
        }
    }

    @Test
    fun should_start_rabbitmq_listener_by_id (){
        // Given
        val id = "listener_id"
        val messageListenerContainer:MessageListenerContainer = mock(MessageListenerContainer::class.java)
        `when`(rabbitListenerEndpointRegistry.getListenerContainer(eq(id))).thenReturn(messageListenerContainer)

        // When
        rabbitMQListenerManager.start(id)

        // Then
        verify(messageListenerContainer, times(1)).start()
    }

    @Test
    fun should_not_start_rabbitmq_listener_by_id_when_not_found (){
        // Given && When && Then
        Assertions.assertThrows(ListenerContainerNotFound::class.java) {
            rabbitMQListenerManager.start("not_found")
        }
    }

    @Test
    fun should_start_all_rabbitmq_listeners (){
        // Given

        // When
        rabbitMQListenerManager.startAll()

        // Then
        verify(rabbitListenerEndpointRegistry, times(1)).start()
    }

    @Test
    fun should_stop_all_rabbitmq_listeners (){
        // Given

        // When
        rabbitMQListenerManager.stopAll()

        // Then
        verify(rabbitListenerEndpointRegistry, times(1)).stop()
    }

    @Test
    fun should_fetch_rabbitmq_listener_stats (){
        // Given
        val id = "listener_id"
        val messageListenerContainer:MessageListenerContainer = mock(MessageListenerContainer::class.java)
        `when`(rabbitListenerEndpointRegistry.listenerContainerIds).thenReturn(setOf(id))
        `when`(rabbitListenerEndpointRegistry.getListenerContainer(id)).thenReturn(messageListenerContainer)
        `when`(messageListenerContainer.isRunning).thenReturn(true)

        // When
        val rabbitMQListeners:HashSet<RabbitMQListener> = rabbitMQListenerManager.fetchAllListenersState() as HashSet<RabbitMQListener>

        // Then
        assertThat(rabbitMQListeners.iterator().next().id).isEqualTo(id)
        assertThat(rabbitMQListeners.iterator().next().running).isTrue
    }
}