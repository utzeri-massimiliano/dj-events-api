package com.massimilianodj.dj_events_api.service;

import com.massimilianodj.dj_events_api.dto.CreateEventDto;
import com.massimilianodj.dj_events_api.dto.EventDto;
import com.massimilianodj.dj_events_api.entity.Event;
import com.massimilianodj.dj_events_api.exception.EventNotFoundException;
import com.massimilianodj.dj_events_api.mapper.EventMapper;
import com.massimilianodj.dj_events_api.repository.EventRepository;
import org.springframework.stereotype.Service;

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
}
