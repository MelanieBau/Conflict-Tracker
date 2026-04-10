package com.nicole.conflicttracker.mapper;

import com.nicole.conflicttracker.dto.*;
import com.nicole.conflicttracker.entity.*;
import com.nicole.conflicttracker.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ConflictMapper {

    private final CountryRepository countryRepository;

    public ConflictMapper(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public ConflictResponseDto toDto(Conflict conflict) {
        ConflictResponseDto dto = new ConflictResponseDto();
        dto.id = conflict.getId();
        dto.name = conflict.getName();
        dto.startDate = conflict.getStartDate();
        dto.status = conflict.getStatus();
        dto.description = conflict.getDescription();
        dto.countries = conflict.getCountries()
                .stream()
                .map(Country::getCode)
                .collect(Collectors.toSet());
        return dto;
    }

    public Conflict toEntity(ConflictRequestDto dto) {
        Conflict conflict = new Conflict();
        conflict.setName(dto.getName());
        conflict.setStartDate(dto.getStartDate());
        conflict.setStatus(dto.getStatus());
        conflict.setDescription(dto.getDescription());

        if (dto.getCountryCodes() != null && !dto.getCountryCodes().isEmpty()) {
            Set<Country> countries = new HashSet<>();
            for (String code : dto.getCountryCodes()) {
                countryRepository.findByCode(code).ifPresent(countries::add);
            }
            conflict.setCountries(countries);
        }

        return conflict;
    }
}