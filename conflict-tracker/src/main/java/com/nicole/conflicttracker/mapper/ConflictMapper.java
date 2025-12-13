package com.nicole.conflicttracker.mapper;

import com.nicole.conflicttracker.dto.*;
import com.nicole.conflicttracker.entity.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ConflictMapper {

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
}