package ru.javajabka.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javajabka.model.Event;
import ru.javajabka.model.EventDTO;
import ru.javajabka.repository.Mapper.EventMapper;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private static final String INSERT = """
    INSERT INTO event_service.event(event_name, task_id, change_from, change_to, event_date_time)
    VALUES (:eventName, :taskId, :from, :to, :eventDateTime)
    RETURNING *;
    """;

    private static final String FIND_ALL_EVENTS_BY_TASK_ID = """
            SELECT *  FROM event_service.event
            WHERE task_id = :taskId;
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final EventMapper eventMapper;

    public Event insert(EventDTO eventDTO) {
        Event event = Event.builder()
                .eventName(eventDTO.getEventName())
                .taskId(eventDTO.getTaskId())
                .from(eventDTO.getFrom())
                .to(eventDTO.getTo())
                .eventDateTime(eventDTO.getEvent_date_time())
                .build();

        return jdbcTemplate.queryForObject(INSERT, eventToSql(event), eventMapper);
    }

    public List<Event> findAllEvents(Long taskId) {
        return jdbcTemplate.query(FIND_ALL_EVENTS_BY_TASK_ID, new MapSqlParameterSource("taskId", taskId), eventMapper);
    }

    private MapSqlParameterSource eventToSql(Event event) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("eventName", event.getEventName());
        params.addValue("taskId", event.getTaskId());
        params.addValue("from", event.getFrom());
        params.addValue("to", event.getTo());
        params.addValue("eventDateTime", event.getEventDateTime());

        return params;
    }
}