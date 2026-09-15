package com.massimilianodj.dj_events_api.mapper;

import com.massimilianodj.dj_events_api.dto.CreateEventDto;
import com.massimilianodj.dj_events_api.dto.EventDto;
import com.massimilianodj.dj_events_api.dto.UpdateEventDto;
import com.massimilianodj.dj_events_api.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {
    EventDto toDto(Event event);

    Event toEntity(EventDto eventDto);

    Event toEntity(CreateEventDto createEventDto);

    void updateEntity(CreateEventDto createEventDto,
                      @MappingTarget Event event);

    void patchEntity(UpdateEventDto UpdateEventDto,
                     @MappingTarget Event event);
}
