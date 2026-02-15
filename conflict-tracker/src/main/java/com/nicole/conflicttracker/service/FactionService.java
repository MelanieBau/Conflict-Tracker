package com.nicole.conflicttracker.service;

import com.nicole.conflicttracker.dto.FactionRequestDto;
import com.nicole.conflicttracker.dto.FactionResponseDto;

import java.util.List;

public interface FactionService {

    List<FactionResponseDto> getAll();

    FactionResponseDto getById(Long id);

    FactionResponseDto create(FactionRequestDto dto);

    FactionResponseDto update(Long id, FactionRequestDto dto);

    void delete(Long id);
}