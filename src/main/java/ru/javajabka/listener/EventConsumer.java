package ru.javajabka.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.javajabka.model.EventDTO;
import ru.javajabka.repository.EventRepository;
import ru.javajabka.service.EventService;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EventConsumer {

    private final EventService eventService;

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receive(List<EventDTO> events) {
        eventService.createEvent(events);
    }
}