package com.nicole.conflicttracker.dto;

import java.util.Set;

public class FactionResponseDto {

    private Long id;
    private String name;
    private Long conflictId;
    private Set<String> countryCodes;

    public Long getId() { return id; }
    public String getName() { return name; }
    public Long getConflictId() { return conflictId; }
    public Set<String> getCountryCodes() { return countryCodes; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
    public void setCountryCodes(Set<String> countryCodes) { this.countryCodes = countryCodes; }
}