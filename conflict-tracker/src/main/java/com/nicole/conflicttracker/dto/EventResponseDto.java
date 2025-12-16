package com.nicole.conflicttracker.dto;

import java.time.LocalDate;

public class EventResponseDto {

    private Long id;
    private LocalDate eventDate;
    private String location;
    private String description;
    private Long conflictId;

    public EventResponseDto() {}

    public EventResponseDto(Long id, LocalDate eventDate, String location, String description, Long conflictId) {
        this.id = id;
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.conflictId = conflictId;
    }

    public Long getId() { return id; }
    public LocalDate getEventDate() { return eventDate; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public Long getConflictId() { return conflictId; }

    public void setId(Long id) { this.id = id; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
}