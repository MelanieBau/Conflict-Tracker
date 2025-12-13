package com.nicole.conflicttracker.service;

import com.nicole.conflicttracker.dto.*;
import com.nicole.conflicttracker.entity.ConflictStatus;

import java.util.*;

public interface ConflictService {
    List<ConflictResponseDto> getAll(Optional<ConflictStatus> status);
    ConflictResponseDto getById(Long id);
    ConflictResponseDto create(ConflictRequestDto dto);
    void delete(Long id);

    List<ConflictResponseDto> getByCountryCode(String code);
}