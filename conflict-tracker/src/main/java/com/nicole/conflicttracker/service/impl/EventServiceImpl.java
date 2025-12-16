package com.nicole.conflicttracker.service.impl;

import com.nicole.conflicttracker.dto.EventRequestDto;
import com.nicole.conflicttracker.dto.EventResponseDto;
import com.nicole.conflicttracker.entity.Conflict;
import com.nicole.conflicttracker.entity.Event;
import com.nicole.conflicttracker.mapper.EventMapper;
import com.nicole.conflicttracker.repository.ConflictRepository;
import com.nicole.conflicttracker.repository.EventRepository;
import com.nicole.conflicttracker.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ConflictRepository conflictRepository;
    private final EventMapper mapper;

    public EventServiceImpl(EventRepository eventRepository,
                            ConflictRepository conflictRepository,
                            EventMapper mapper) {
        this.eventRepository = eventRepository;
        this.conflictRepository = conflictRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EventResponseDto> getAll() {
        return eventRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public EventResponseDto getById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
        return mapper.toDto(event);
    }

    @Override
    public EventResponseDto create(EventRequestDto dto) {
        Conflict conflict = conflictRepository.findById(dto.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id " + dto.getConflictId()));

        Event event = new Event();
        event.setEventDate(dto.getEventDate());
        event.setLocation(dto.getLocation());
        event.setDescription(dto.getDescription());
        event.setConflict(conflict);

        return mapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventResponseDto update(Long id, EventRequestDto dto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));

        Conflict conflict = conflictRepository.findById(dto.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id " + dto.getConflictId()));

        event.setEventDate(dto.getEventDate());
        event.setLocation(dto.getLocation());
        event.setDescription(dto.getDescription());
        event.setConflict(conflict);

        return mapper.toDto(eventRepository.save(event));
    }

    @Override
    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id " + id);
        }
        eventRepository.deleteById(id);
    }
}