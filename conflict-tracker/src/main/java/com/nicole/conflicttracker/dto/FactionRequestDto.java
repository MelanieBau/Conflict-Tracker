package com.nicole.conflicttracker.dto;

import java.util.Set;

public class FactionRequestDto {

    private String name;
    private Long conflictId;
    private Set<String> countryCodes;

    public String getName() { return name; }
    public Long getConflictId() { return conflictId; }
    public Set<String> getCountryCodes() { return countryCodes; }

    public void setName(String name) { this.name = name; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
    public void setCountryCodes(Set<String> countryCodes) { this.countryCodes = countryCodes; }
}