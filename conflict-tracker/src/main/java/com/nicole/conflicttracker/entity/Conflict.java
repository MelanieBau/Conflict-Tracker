package com.nicole.conflicttracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "conflicts")
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private ConflictStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "conflict_countries",
            joinColumns = @JoinColumn(name = "conflict_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> countries = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ConflictStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStatus(ConflictStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}