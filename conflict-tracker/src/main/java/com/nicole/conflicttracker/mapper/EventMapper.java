package com.nicole.conflicttracker.mapper;

import com.nicole.conflicttracker.dto.EventResponseDto;
import com.nicole.conflicttracker.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventResponseDto toDto(Event event) {
        Long conflictId = event.getConflict() != null ? event.getConflict().getId() : null;

        return new EventResponseDto(
                event.getId(),
                event.getEventDate(),
                event.getLocation(),
                event.getDescription(),
                conflictId
        );
    }
}