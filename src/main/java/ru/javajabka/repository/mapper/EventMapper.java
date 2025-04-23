package ru.javajabka.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.javajabka.model.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class EventMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Event.builder()
                .id(rs.getLong("id"))
                .eventName(rs.getString("event_name"))
                .taskId(rs.getLong("task_id"))
                .from(rs.getString("change_from"))
                .to(rs.getString("change_to"))
                .eventDateTime(rs.getObject("event_date_time", LocalDateTime.class))
                .build();
    }
}