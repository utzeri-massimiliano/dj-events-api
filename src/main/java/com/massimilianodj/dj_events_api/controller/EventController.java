package com.massimilianodj.dj_events_api.controller;

import com.massimilianodj.dj_events_api.dto.CreateEventDto;
import com.massimilianodj.dj_events_api.dto.EventDto;
import com.massimilianodj.dj_events_api.dto.UpdateEventDto;
import com.massimilianodj.dj_events_api.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping
    public EventDto createEvent(@Valid @RequestBody CreateEventDto createEventDto) {
        return eventService.createEvent(createEventDto);
    }

    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable Long id, @Valid @RequestBody CreateEventDto createEventDto) {
        return eventService.updateEvent(id, createEventDto);
    }

    @GetMapping("/city/{city}")
    public List<EventDto> getEventsByCity(@PathVariable String city) {
        return eventService.getEventsByCity(city);
    }

    @GetMapping("/search")
    public List<EventDto> getEventsByCityAndDate(@RequestParam String city, @RequestParam LocalDate date) {
        return eventService.getEventsByCityAndDate(city, date);
    }

    @GetMapping("/search/{title}")
    public List<EventDto> getEventsContainingTitle(@PathVariable String title) {
        return eventService.getEventsByTitleContains(title);
    }

    @PatchMapping("/{id}")
    public EventDto patchEvent(@PathVariable Long id, @Valid @RequestBody UpdateEventDto updateEventDto) {
        return eventService.patchEvent(id, updateEventDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
