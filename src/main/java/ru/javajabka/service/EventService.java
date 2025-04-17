package ru.javajabka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javajabka.model.Event;
import ru.javajabka.repository.EventRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getAllEvents(final Long taskId) {
        return eventRepository.findAllEvents(taskId);
    }
}