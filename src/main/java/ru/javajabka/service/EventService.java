package ru.javajabka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javajabka.model.Event;
import ru.javajabka.model.EventDTO;
import ru.javajabka.repository.EventRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createEvent(List<EventDTO> events) {
        eventRepository.insert(events);
    }

    @Transactional(readOnly = true)
    public List<Event> getAllEvents(final Long taskId) {
        return eventRepository.findAllEvents(taskId);
    }
}