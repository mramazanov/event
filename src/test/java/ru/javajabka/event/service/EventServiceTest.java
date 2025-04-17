package ru.javajabka.event.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javajabka.model.Event;
import ru.javajabka.repository.EventRepository;
import ru.javajabka.service.EventService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void findAllEvents() {
        Event event_one = buildEvent(1L, "task_created", null, null, LocalDateTime.of(2025, 5, 5, 12, 30));
        Event event_two = buildEvent(1L, "title_changed", "test", "test2", LocalDateTime.of(2025, 5, 5, 12, 45));
        Event event_three = buildEvent(1L, "assignee_changed", "1", "2", LocalDateTime.of(2025, 5, 5, 12, 55));
        List<Event> events = Arrays.asList(event_one, event_two, event_three);
        Mockito.when(eventRepository.findAllEvents(1L)).thenReturn(events);
        List<Event> foundEvents = eventService.getAllEvents(1L);
        Assertions.assertEquals(events, foundEvents);
        Mockito.verify(eventRepository).findAllEvents(1L);
    }

    private Event buildEvent(Long taskId, String eventName, String from, String to, LocalDateTime eventDateTime) {
        return Event.builder()
                .id(taskId)
                .eventName(eventName)
                .from(from)
                .to(to)
                .eventDateTime(eventDateTime)
                .build();
    }
}