package com.massimilianodj.dj_events_api.config;

import com.massimilianodj.dj_events_api.entity.Event;
import com.massimilianodj.dj_events_api.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    private final EventRepository eventRepository;

    public DataInitializer(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (eventRepository.count() == 0) {
            eventRepository.save(new Event(
                    "Tomorrowland Belgium",
                    "The world's largest electronic music festival.",
                    LocalDate.of(2026, 7, 17),
                    "De Schorre",
                    "Boom",
                    "https://example.com/images/tomorrowland.jpg"
            ));
            eventRepository.save(new Event(
                    "Ultra Europe",
                    "One of Europe's biggest electronic music festivals.",
                    LocalDate.of(2026, 7, 10),
                    "Park Mladeži",
                    "Split",
                    "https://example.com/images/ultra-europe.jpg"
            ));
        }

    }
}
