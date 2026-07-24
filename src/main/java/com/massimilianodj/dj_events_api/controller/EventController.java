package com.massimilianodj.dj_events_api.controller;

import com.massimilianodj.dj_events_api.dto.EventDto;
import com.massimilianodj.dj_events_api.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing endpoints for managing DJ events.
 */
@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Returns all available DJ events.
     *
     * @return list of events.
     */
    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}
