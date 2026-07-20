package com.massimilianodj.dj_events_api.service;

import com.massimilianodj.dj_events_api.entity.Event;
import com.massimilianodj.dj_events_api.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        events.add(new Event(
                1L,
                "Tomorrowland Belgium",
                "The world's largest electronic music festival.",
                LocalDate.of(2026, 7, 17),
                "De Schorre",
                "Boom",
                "https://example.com/images/tomorrowland.jpg"
        ));

        events.add(new Event(
                2L,
                "Ultra Europe",
                "One of Europe's biggest electronic music festivals.",
                LocalDate.of(2026, 7, 10),
                "Park Mladeži",
                "Split",
                "https://example.com/images/ultra-europe.jpg"
        ));
        return events;
    }
}
