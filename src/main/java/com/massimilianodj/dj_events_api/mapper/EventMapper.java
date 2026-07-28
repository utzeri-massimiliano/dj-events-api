package com.massimilianodj.dj_events_api.mapper;

import com.massimilianodj.dj_events_api.dto.CreateEventDto;
import com.massimilianodj.dj_events_api.dto.EventDto;
import com.massimilianodj.dj_events_api.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto toDto(Event event);

    Event toEntity(EventDto eventDto);

    Event toEntity(CreateEventDto createEventDto);
}
