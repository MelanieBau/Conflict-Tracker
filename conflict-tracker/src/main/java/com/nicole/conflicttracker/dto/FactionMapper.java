package com.nicole.conflicttracker.mapper;

import com.nicole.conflicttracker.dto.FactionResponseDto;
import com.nicole.conflicttracker.entity.Country;
import com.nicole.conflicttracker.entity.Faction;

import java.util.Set;
import java.util.stream.Collectors;

public class FactionMapper {

    private FactionMapper() {}

    public static FactionResponseDto toDto(Faction faction) {
        FactionResponseDto dto = new FactionResponseDto();
        dto.setId(faction.getId());
        dto.setName(faction.getName());

        if (faction.getConflict() != null) {
            dto.setConflictId(faction.getConflict().getId());
        }

        Set<String> codes = faction.getCountries().stream()
                .map(Country::getCode)
                .collect(Collectors.toSet());

        dto.setCountryCodes(codes);
        return dto;
    }
}