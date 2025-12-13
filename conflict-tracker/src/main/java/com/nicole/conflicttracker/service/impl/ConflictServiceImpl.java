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
    private final CountryRepository countryRepository;
    private final ConflictMapper mapper;

    public ConflictServiceImpl(ConflictRepository conflictRepository,
                               CountryRepository countryRepository,
                               ConflictMapper mapper) {
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ConflictResponseDto> getAll(Optional<ConflictStatus> status) {
        List<Conflict> conflicts = status
                .map(conflictRepository::findByStatus)
                .orElseGet(conflictRepository::findAll);

        return conflicts.stream().map(mapper::toDto).toList();
    }

    @Override
    public ConflictResponseDto getById(Long id) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found"));
        return mapper.toDto(conflict);
    }

    @Override
    public ConflictResponseDto create(ConflictRequestDto dto) {
        Conflict conflict = new Conflict();
        conflict.setName(dto.name);
        conflict.setStartDate(dto.startDate);
        conflict.setStatus(dto.status);
        conflict.setDescription(dto.description);

        if (dto.countryCodes != null) {
            Set<Country> countries = new HashSet<>();
            for (String code : dto.countryCodes) {
                Country c = countryRepository.findByCode(code)
                        .orElseThrow(() -> new RuntimeException("Country not found: " + code));
                countries.add(c);
            }
            conflict.setCountries(countries);
        }

        return mapper.toDto(conflictRepository.save(conflict));
    }

    @Override
    public void delete(Long id) {
        conflictRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConflictResponseDto> getByCountryCode(String code) {
        return conflictRepository.findByCountryCode(code)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}