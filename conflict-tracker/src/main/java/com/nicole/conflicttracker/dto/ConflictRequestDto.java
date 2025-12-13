package com.nicole.conflicttracker.dto;

import com.nicole.conflicttracker.entity.ConflictStatus;
import java.time.LocalDate;
import java.util.Set;

public class ConflictRequestDto {
    public String name;
    public LocalDate startDate;
    public ConflictStatus status;
    public String description;
    public Set<String> countryCodes;
}
