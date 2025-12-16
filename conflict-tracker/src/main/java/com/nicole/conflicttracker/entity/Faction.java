package com.nicole.conflicttracker.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "factions")
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Many factions -> one conflict
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "conflict_id", nullable = false)
    private Conflict conflict;

    // Many factions <-> many countries (supporters)
    @ManyToMany
    @JoinTable(
            name = "faction_countries",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> countries = new HashSet<>();

    public Long getId() { return id; }
    public String getName() { return name; }
    public Conflict getConflict() { return conflict; }
    public Set<Country> getCountries() { return countries; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setConflict(Conflict conflict) { this.conflict = conflict; }
    public void setCountries(Set<Country> countries) { this.countries = countries; }
}