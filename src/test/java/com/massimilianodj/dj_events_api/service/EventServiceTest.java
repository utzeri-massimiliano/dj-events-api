package com.massimilianodj.dj_events_api.service;

import com.massimilianodj.dj_events_api.dto.CreateEventDto;
import com.massimilianodj.dj_events_api.dto.EventDto;
import com.massimilianodj.dj_events_api.dto.UpdateEventDto;
import com.massimilianodj.dj_events_api.entity.Event;
import com.massimilianodj.dj_events_api.exception.EventNotFoundException;
import com.massimilianodj.dj_events_api.mapper.EventMapper;
import com.massimilianodj.dj_events_api.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private EventService eventService;

    @Test
    void shouldReturnEventsByCity() {
        String city = "Roma";

        Event event = mock(Event.class);
        EventDto eventDto = mock(EventDto.class);

        when(eventRepository.findByCity(city))
                .thenReturn(List.of(event));

        when(eventMapper.toDto(event))
                .thenReturn(eventDto);

        List<EventDto> result = eventService.getEventsByCity(city);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(eventDto, result.getFirst());

        verify(eventRepository).findByCity(city);
        verify(eventMapper).toDto(event);
    }

    @Test
    void shouldReturnEmptyListWhenCityHasNoEvents() {
        String city = "Sidney";

        when(eventRepository.findByCity(city))
                .thenReturn(List.of());

        List<EventDto> result = eventService.getEventsByCity(city);

        Assertions.assertTrue(result.isEmpty());

        verify(eventRepository).findByCity(city);
        verifyNoInteractions(eventMapper);
    }

    @Test
    void shouldReturnEventsByCityAndDate() {
        String city = "Roma";
        LocalDate date = LocalDate.of(2026, 8, 19);

        Event event = mock(Event.class);
        EventDto eventDto = mock(EventDto.class);

        when(eventRepository.findByCityAndDate(city, date))
                .thenReturn(List.of(event));

        when(eventMapper.toDto(event))
                .thenReturn(eventDto);

        List<EventDto> result = eventService.getEventsByCityAndDate(city, date);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(eventDto, result.getFirst());

        verify(eventRepository).findByCityAndDate(city, date);
        verify(eventMapper).toDto(event);
    }

    @Test
    void shouldReturnEmptyListWhenCityAndDateHaveNoEvents() {
        String city = "Roma";
        LocalDate date = LocalDate.of(2026, 8, 5);

        when(eventRepository.findByCityAndDate(city, date))
                .thenReturn(List.of());

        List<EventDto> result = eventService.getEventsByCityAndDate(city, date);

        Assertions.assertTrue(result.isEmpty());

        verify(eventRepository).findByCityAndDate(city, date);
        verifyNoInteractions(eventMapper);
    }

    @Test
    void shouldReturnAllEvents() {
        Event event = mock(Event.class);
        EventDto eventDto = mock(EventDto.class);

        when(eventRepository.findAll())
                .thenReturn(List.of(event));

        when(eventMapper.toDto(event))
                .thenReturn(eventDto);

        List<EventDto> result = eventService.getAllEvents();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(eventDto, result.getFirst());

        verify(eventRepository).findAll();
        verify(eventMapper).toDto(event);
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoEvents() {
        when(eventRepository.findAll())
                .thenReturn(List.of());

        List<EventDto> result = eventService.getAllEvents();

        Assertions.assertTrue(result.isEmpty());

        verify(eventRepository).findAll();
        verifyNoInteractions(eventMapper);
    }

    @Test
    void shouldReturnEventsWhenTitleContainsString() {
        Event event = mock(Event.class);
        EventDto eventDto = mock(EventDto.class);
        String title = "matchedString";

        when(eventRepository.findByTitleContains(title))
                .thenReturn(List.of(event));

        when(eventMapper.toDto(event))
                .thenReturn(eventDto);

        List<EventDto> result = eventService.getEventsByTitleContains(title);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(eventDto, result.getFirst());

        verify(eventRepository).findByTitleContains(title);
        verify(eventMapper).toDto(event);
    }

    @Test
    void shouldReturnEmptyListWhenTitleNotContainsString() {
        String title = "matchedString";

        when(eventRepository.findByTitleContains(title))
                .thenReturn(List.of());

        List<EventDto> result = eventService.getEventsByTitleContains(title);

        Assertions.assertTrue(result.isEmpty());

        verify(eventRepository).findByTitleContains(title);
        verifyNoInteractions(eventMapper);
    }

    @Test
    void shouldRetrieveEventById() {
        Long id = 1L;
        Event event = mock(Event.class);
        EventDto eventDto = mock(EventDto.class);

        when(eventRepository.findById(id))
                .thenReturn(Optional.of(event));

        when(eventMapper.toDto(event))
                .thenReturn(eventDto);

        EventDto result = eventService.getEventById(id);

        Assertions.assertEquals(eventDto, result);

        verify(eventRepository).findById(id);
        verify(eventMapper).toDto(event);
    }

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        Long id = 1L;

        when(eventRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EventNotFoundException.class,
                () -> eventService.getEventById(id));

        verify(eventRepository).findById(id);
        verifyNoInteractions(eventMapper);
    }

    @Test
    void shouldCreateEvent() {
        CreateEventDto createEventDto = mock(CreateEventDto.class);
        Event event = mock(Event.class);
        EventDto eventDto = mock(EventDto.class);

        when(eventMapper.toEntity(createEventDto))
                .thenReturn(event);

        when(eventRepository.save(any(Event.class)))
                .thenReturn(event);

        when(eventMapper.toDto(event))
                .thenReturn(eventDto);

        EventDto result = eventService.createEvent(createEventDto);

        Assertions.assertEquals(eventDto, result);

        verify(eventMapper).toEntity(createEventDto);
        verify(eventRepository).save(event);
        verify(eventMapper).toDto(event);
    }

    @Test
    void shouldUpdateEvent() {
        Long id = 1L;
        CreateEventDto createEventDto = mock(CreateEventDto.class);
        Event event = mock(Event.class);
        EventDto eventDto = mock(EventDto.class);

        when(eventRepository.save(any(Event.class)))
                .thenReturn(event);

        when(eventMapper.toDto(event))
                .thenReturn(eventDto);

        when(eventRepository.findById(id))
                .thenReturn(Optional.of(event));

        EventDto result = eventService.updateEvent(id, createEventDto);

        Assertions.assertEquals(eventDto, result);

        verify(eventMapper).updateEntity(createEventDto, event);
        verify(eventRepository).findById(id);
        verify(eventMapper).toDto(event);
        verify(eventRepository).save(event);
    }

    @Test
    void shouldThrowExceptionWhenEventToUpdateDoesNotExist() {
        Long id = 1L;
        CreateEventDto createEventDto = mock(CreateEventDto.class);

        when(eventRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EventNotFoundException.class,
                () -> eventService.updateEvent(id, createEventDto));

        verify(eventRepository).findById(id);
        verifyNoInteractions(eventMapper);
    }

    @Test
    void shouldDeleteEvent() {
        Long id = 1L;
        Event event = mock(Event.class);

        when(eventRepository.findById(id))
                .thenReturn(Optional.of(event));

        eventService.deleteEvent(id);

        verify(eventRepository).findById(id);
        verify(eventRepository).delete(event);
    }

    @Test
    void shouldNotDeleteEvent() {
        Long id = 1L;

        when(eventRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EventNotFoundException.class,
                () -> eventService.deleteEvent(id));

        verify(eventRepository).findById(id);
        verify(eventRepository, never()).delete(any(Event.class));
    }

    @Test
    void shouldPatchEvent() {
        Long id = 1L;
        UpdateEventDto updateEventDto = mock(UpdateEventDto.class);
        Event event = mock(Event.class);
        EventDto eventDto = mock(EventDto.class);

        when(eventRepository.findById(id))
                .thenReturn(Optional.of(event));

        when(eventRepository.save(any(Event.class)))
                .thenReturn(event);

        when(eventMapper.toDto(event))
                .thenReturn(eventDto);

        EventDto result = eventService.patchEvent(id, updateEventDto);

        Assertions.assertEquals(eventDto, result);

        verify(eventRepository).findById(id);
        verify(eventMapper).patchEntity(updateEventDto, event);
        verify(eventRepository).save(event);
        verify(eventMapper).toDto(event);
    }

    @Test
    void shouldNotPatchEvent() {
        Long id = 1L;
        UpdateEventDto updateEventDto = mock(UpdateEventDto.class);

        when(eventRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EventNotFoundException.class,
                () -> eventService.patchEvent(id, updateEventDto));

        verify(eventRepository).findById(id);
        verifyNoInteractions(eventMapper);
    }
}
