package com.massimilianodj.dj_events_api.repository;

import com.massimilianodj.dj_events_api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
