package com.massimilianodj.dj_events_api.repository;

import com.massimilianodj.dj_events_api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCity(String city);
}
