package ru.javajabka.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.javajabka.model.EventDTO;
import ru.javajabka.repository.EventRepository;

@RequiredArgsConstructor
@Component
public class EventConsumer {

    private final EventRepository eventRepository;

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receive(EventDTO eventDTO) {
        eventRepository.insert(eventDTO);
    }
}