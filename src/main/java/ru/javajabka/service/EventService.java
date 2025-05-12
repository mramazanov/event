package ru.javajabka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javajabka.model.Event;
import ru.javajabka.model.EventDTO;
import ru.javajabka.repository.EventRepository;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createEvent(List<EventDTO> events) {
        eventRepository.insert(events);
    }

    @Transactional(readOnly = true)
    public List<Event> getAllEvents(final Set<Long> taskIds) {
        validate(taskIds);
        return eventRepository.findAllEvents(taskIds);
    }

    private void validate(Set<Long> taskIds) {
        if (taskIds.isEmpty()) {
            throw new RuntimeException("Список задача пустой");
        }
    }
}