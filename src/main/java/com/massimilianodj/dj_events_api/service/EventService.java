package com.massimilianodj.dj_events_api.service;

import com.massimilianodj.dj_events_api.dto.CreateEventDto;
import com.massimilianodj.dj_events_api.dto.EventDto;
import com.massimilianodj.dj_events_api.dto.UpdateEventDto;
import com.massimilianodj.dj_events_api.entity.Event;
import com.massimilianodj.dj_events_api.exception.EventNotFoundException;
import com.massimilianodj.dj_events_api.mapper.EventMapper;
import com.massimilianodj.dj_events_api.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service responsible for managing DJ events.
 */
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    /**
     * Retrieves all available DJ events.
     *
     * @return Dto list of event DTOs stored in the database
     */
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all DJ events in a specific city.
     *
     * @param city city where the events take place
     * @return list of event DTOs
     */
    public List<EventDto> getEventsByCity(String city) {
        return eventRepository.findByCity(city).stream()
                .map(eventMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all DJ events within a specific city and date.
     *
     * @param city city where the events take place
     * @return list of event DTOs
     */
    public List<EventDto> getEventsByCityAndDate(String city, LocalDate date) {
        return eventRepository.findByCityAndDate(city, date).stream()
                .map(eventMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all DJ events where the title matches the ìnput tile.
     *
     * @param title represents the event title
     * @return list of event DTOs
     */
    public List<EventDto> getEventsByTitleContains(String title) {
        return eventRepository.findByTitleContains(title).stream()
                .map(eventMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a DJ event by its identifier.
     *
     * @param id event identifier
     * @return event DTO
     * @throws EventNotFoundException if the event does not exist
     */
    public EventDto getEventById(Long id) {
        return eventMapper.toDto(eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id)));
    }

    /**
     * Creates a DJ event
     *
     * @param createEventDto represents the event to be persisted
     * @return event DTO
     */
    public EventDto createEvent(CreateEventDto createEventDto) {
        return eventMapper.toDto(
                eventRepository.save(
                        eventMapper.toEntity(createEventDto))
        );
    }

    /**
     * Updates a DJ event by its identifier.
     *
     * @param id event identifier
     * @return event DTO
     * @throws EventNotFoundException if the event does not exist
     */
    public EventDto updateEvent(Long id, CreateEventDto createEventDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        eventMapper.updateEntity(createEventDto, event);
        return eventMapper.toDto(eventRepository.save(event));
    }

    /**
     * Deletes a DJ event by its identifier.
     *
     * @param id event identifier
     * @throws EventNotFoundException if the event does not exist
     */
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        eventRepository.delete(event);
    }

    /**
     * Partially updates a DJ event by its identifier.
     *
     * @param id event identifier
     * @return event DTO
     * @throws EventNotFoundException if the event does not exist
     */
    public EventDto patchEvent(Long id, UpdateEventDto updateEventDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        eventMapper.patchEntity(updateEventDto, event);
        return eventMapper.toDto(eventRepository.save(event));
    }
}
