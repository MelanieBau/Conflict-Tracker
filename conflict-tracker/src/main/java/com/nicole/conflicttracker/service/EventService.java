package com.nicole.conflicttracker.service;

import com.nicole.conflicttracker.dto.EventRequestDto;
import com.nicole.conflicttracker.dto.EventResponseDto;

import java.util.List;

public interface EventService {

    List<EventResponseDto> getAll();
    EventResponseDto getById(Long id);
    EventResponseDto create(EventRequestDto dto);
    EventResponseDto update(Long id, EventRequestDto dto);
    void delete(Long id);
}