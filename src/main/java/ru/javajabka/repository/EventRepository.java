package ru.javajabka.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import ru.javajabka.model.Event;
import ru.javajabka.model.EventDTO;
import ru.javajabka.repository.mapper.EventMapper;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private static final String INSERT = """
            INSERT INTO event_service.event(event_name, task_id, change_from, change_to, event_date_time)
            VALUES (:eventName, :taskId, :from, :to, :eventDateTime)
    """;

    private static final String FIND_ALL_EVENTS_BY_TASK_IDS = """
            SELECT * FROM event_service.event
            WHERE task_id IN (:taskIds);
            """;


    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final EventMapper eventMapper;

    public void insert(List<EventDTO> events) {
        jdbcTemplate.batchUpdate(INSERT, SqlParameterSourceUtils.createBatch(events));
    }

    public List<Event> findAllEvents(Set<Long> taskIds) {
        return jdbcTemplate.query(FIND_ALL_EVENTS_BY_TASK_IDS, new MapSqlParameterSource("taskIds", taskIds), eventMapper);
    }
}