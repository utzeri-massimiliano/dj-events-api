package com.massimilianodj.dj_events_api.service;

import com.massimilianodj.dj_events_api.dto.EventDto;
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
}
