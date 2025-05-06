CREATE SCHEMA event_service;

CREATE TABLE event_service.event (
    id SERIAL PRIMARY KEY,
    event_name VARCHAR NOT NULL,
    task_id INT NOT NULL,
    change_from VARCHAR(50),
    change_to VARCHAR(50),
    event_date_time TIMESTAMP NOT NULL
)