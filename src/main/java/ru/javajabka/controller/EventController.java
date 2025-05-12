package ru.javajabka.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.javajabka.model.Event;
import ru.javajabka.service.EventService;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
@Tag(name = "История задач")
public class EventController {

    private final EventService eventService;

    @GetMapping
    @Operation(summary = "Найти события")
    public List<Event> getEventsTask(@RequestParam final Set<Long> ids) {
        return eventService.getAllEvents(ids);
    }
}