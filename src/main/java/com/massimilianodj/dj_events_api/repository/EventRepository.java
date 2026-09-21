package com.massimilianodj.dj_events_api.repository;

import com.massimilianodj.dj_events_api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCity(String city);

    List<Event> findByCityAndDate(String city, LocalDate date);

    List<Event> findByTitleContains(String title);
}
