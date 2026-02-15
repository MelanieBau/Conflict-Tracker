package com.nicole.conflicttracker.service.impl;

import com.nicole.conflicttracker.dto.*;
import com.nicole.conflicttracker.entity.*;
import com.nicole.conflicttracker.mapper.ConflictMapper;
import com.nicole.conflicttracker.repository.*;
import org.springframework.transaction.annotation.Transactional;
import com.nicole.conflicttracker.service.ConflictService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ConflictServiceImpl implements ConflictService {

    private final ConflictRepository conflictRepository;
    private final ConflictMapper conflictMapper;

    public ConflictServiceImpl(ConflictRepository conflictRepository,
                               ConflictMapper conflictMapper) {
        this.conflictRepository = conflictRepository;
        this.conflictMapper = conflictMapper;
    }

    @Override
    public List<ConflictResponseDto> getAll(Optional<ConflictStatus> status) {
        List<Conflict> conflicts;

        if (status.isPresent()) {
            conflicts = conflictRepository.findByStatus(status.get());
        } else {
            conflicts = conflictRepository.findAll();
        }

        return conflicts.stream()
                .map(conflictMapper::toDto)
                .toList();
    }

    @Override
    public ConflictResponseDto getById(Long id) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found"));

        return conflictMapper.toDto(conflict);
    }

    @Override
    public ConflictResponseDto create(ConflictRequestDto dto) {
        Conflict conflict = conflictMapper.toEntity(dto);
        conflict = conflictRepository.save(conflict);
        return conflictMapper.toDto(conflict);
    }

    @Override
    public ConflictResponseDto update(Long id, ConflictRequestDto dto) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found"));

        conflict.setName(dto.getName());
        conflict.setStartDate(dto.getStartDate());
        conflict.setStatus(dto.getStatus());
        conflict.setDescription(dto.getDescription());

        conflict = conflictRepository.save(conflict);

        return conflictMapper.toDto(conflict);
    }

    @Override
    public void delete(Long id) {
        conflictRepository.deleteById(id);
    }

    @Override
    public List<ConflictResponseDto> getByCountryCode(String code) {
        List<Conflict> conflicts = conflictRepository.findByCountries_Code(code);

        return conflicts.stream()
                .map(conflictMapper::toDto)
                .toList();
    }
}