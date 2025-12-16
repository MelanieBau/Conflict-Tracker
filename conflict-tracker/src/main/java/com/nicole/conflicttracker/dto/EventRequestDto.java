package com.nicole.conflicttracker.dto;

import java.time.LocalDate;

public class EventRequestDto {

    private LocalDate eventDate;
    private String location;
    private String description;
    private Long conflictId;

    public LocalDate getEventDate() { return eventDate; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public Long getConflictId() { return conflictId; }

    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
}